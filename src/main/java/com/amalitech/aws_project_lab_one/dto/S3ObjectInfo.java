package com.amalitech.aws_project_lab_one.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S3ObjectInfo {
    private String name;
    private String url;
}
