package com.ihours.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ihours.model.*;
import com.ihours.model.Error;
import com.ihours.service.ProfileImageService;

@RestController
public class ProfileImageController {

	@Autowired
	ProfileImageService profileImageService;

	@PostMapping("/doUpload")
	public ResponseEntity<?> uploadProfilePicture(@RequestParam(value = "file") CommonsMultipartFile fileUpload,
			HttpSession session) {

		UsersDetails validUser = (UsersDetails) session.getAttribute("validUser");
		if (validUser == null) {
			Error error = new Error(7, "UnAuthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		ProfileImage profileImage = new ProfileImage();
		profileImage.setUsername(validUser.getUsername());
		profileImage.setImage(fileUpload.getBytes());
		profileImageService.saveProfilePicture(profileImage);
		return new ResponseEntity<UsersDetails>(validUser, HttpStatus.OK);
	}

	@GetMapping(value = "/getimage/{username}")
	public @ResponseBody byte[] getProfilePic(@PathVariable String username, HttpSession session) {
		UsersDetails validUser = (UsersDetails) session.getAttribute("validUser");
		if (validUser == null)
			return null;
		else {
			ProfileImage profileImage = profileImageService.getProfilePicture(username);
			if (profileImage == null)
				return null;
			else
				return profileImage.getImage();
		}

	}

}
