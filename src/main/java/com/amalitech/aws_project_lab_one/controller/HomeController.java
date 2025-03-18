package com.amalitech.aws_project_lab_one.controller;

import com.amalitech.aws_project_lab_one.dto.S3ObjectInfo;
import com.amalitech.aws_project_lab_one.service.ObjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ObjectsService objectsService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<S3ObjectInfo> objects = objectsService.getObjects();
        model.addAttribute("images", objects);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            String result = objectsService.uploadObject(file);
            redirectAttributes.addFlashAttribute("message", result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error uploading file: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") String fileId, RedirectAttributes redirectAttributes) {
        try {
            String result = objectsService.deleteObjectByName(fileId);
            redirectAttributes.addFlashAttribute("message", result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting file: " + e.getMessage());
        }
        return "redirect:/";
    }
}