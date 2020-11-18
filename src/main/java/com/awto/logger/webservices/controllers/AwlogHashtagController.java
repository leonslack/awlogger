package com.awto.logger.webservices.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awto.logger.commons.exceptions.CustomBaseException;
import com.awto.logger.data.models.AwlogHashtag;
import com.awto.logger.services.IAwlogHashtagService;
import com.awto.logger.webservices.dtos.response.SimpleResponseStructure;

@RestController
@RequestMapping("/hastags")
public class AwlogHashtagController extends CustomBaseController {

	private IAwlogHashtagService service;

	Logger logger = LogManager.getLogger();

	@Autowired
	public AwlogHashtagController(IAwlogHashtagService service) {
		super();
		this.service = service;
	}

	@CrossOrigin(origins = "*")
	@PutMapping()
	public ResponseEntity<SimpleResponseStructure<AwlogHashtag>> addComment(@RequestBody AwlogHashtag toUpdate)
			throws CustomBaseException {

		SimpleResponseStructure<AwlogHashtag> response = new SimpleResponseStructure<>();

		response.setData(service.updateHashtag(toUpdate));
		logger.debug("hashtag updated! ");
		return ResponseEntity.ok(response);
	}

}
