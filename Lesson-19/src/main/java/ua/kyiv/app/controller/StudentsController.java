package ua.kyiv.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ua.kyiv.app.domain.Student;
import ua.kyiv.app.dto.StudentResponse;
import ua.kyiv.app.service.StudentService;

@Controller
public class StudentsController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String index() {
		return "uploadStudent";
	}

	@PostMapping("/uploadStudent")
	public String uploadStudent(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("age") Integer age,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws NumberFormatException, IOException {
		if (file.isEmpty()) {
			return "redirect:uploadStatus";
		}
		Student student = studentService.saveStudent(firstName, lastName, age, file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadStudent/")
				.path(student.getId()).toUriString();
		
		StudentResponse studentResponse = new StudentResponse(student.getFirstName(), student.getLastName(), student.getAge(), file.getName(),
				file.getContentType(), fileDownloadUri, file.getSize());
		
		redirectAttributes.addFlashAttribute("dataForNextPage", studentResponse);
		
		return "redirect:/uploadStatus";
	}
	
	@GetMapping("/uploadStatus")
	public String uploadStatus(@ModelAttribute("dataForNextPage") StudentResponse studentResponse, Model model) {
		model.addAttribute("student", studentResponse);
		return "uploadStatus";
	}

	@GetMapping("/downloadStudent/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String id) throws FileNotFoundException {
		Student student = studentService.getStudentById(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(student.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + student.getFileName() + "\"")
				.body(new ByteArrayResource(student.getData()));
	}

}
