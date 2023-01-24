package com.example.application.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvatarImage {
    /**
     * Class for holding data for an attachment file in this case an Image.
     */
    private byte[] image;
    private String name;
    private String mime;
}
