package com.awto.logger.webservices.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awto.logger.commons.exceptions.CustomBaseException;
import com.awto.logger.data.predicates.AwlogLoggerPredicates;
import com.awto.logger.services.IAwlogLoggerService;
import com.awto.logger.webservices.dtos.request.AwlogLoggerRequest;
import com.awto.logger.webservices.dtos.response.AwlogLoggerResponse;
import com.awto.logger.webservices.dtos.response.SimpleResponseStructure;

@RestController
@RequestMapping("/logs")
public class AwlogLoggerController extends CustomBaseController {

	Logger logger = LogManager.getLogger();

	private IAwlogLoggerService awlogLoggerService;

	@Autowired
	public AwlogLoggerController(IAwlogLoggerService awlogLoggerService) {
		super();
		this.awlogLoggerService = awlogLoggerService;
	}

	@CrossOrigin(origins = "*")
	@PostMapping()
	public ResponseEntity<SimpleResponseStructure<AwlogLoggerResponse>> addComment(
			@RequestBody AwlogLoggerRequest newlog) throws CustomBaseException {

		SimpleResponseStructure<AwlogLoggerResponse> response = new SimpleResponseStructure<>();

		response.setData(AwlogLoggerResponse.fromEntity(awlogLoggerService.createLog(newlog)));
		logger.debug("Added log! ");
		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "*")
	@GetMapping()
	public ResponseEntity<SimpleResponseStructure<List<AwlogLoggerResponse>>> getLogs() throws CustomBaseException {
		logger.debug("Trying get logs! ");
		SimpleResponseStructure<List<AwlogLoggerResponse>> response = new SimpleResponseStructure<>();

		response.setData(awlogLoggerService.getLogs(AwlogLoggerPredicates.idNotNull()).stream()
				.map(AwlogLoggerResponse::fromEntity).collect(Collectors.toList()));
		logger.debug("Added log! ");
		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/{hashtag}/hashtag")
	public ResponseEntity<SimpleResponseStructure<List<AwlogLoggerResponse>>> getLogsByHashtag(
			@PathVariable("hashtag") String hashtag) throws CustomBaseException {
		logger.debug("Trying get logs! ");
		SimpleResponseStructure<List<AwlogLoggerResponse>> response = new SimpleResponseStructure<>();

		response.setData(awlogLoggerService.getLogs(AwlogLoggerPredicates.hashtagEq(hashtag)).stream()
				.map(AwlogLoggerResponse::fromEntity).collect(Collectors.toList()));
		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/{id}")
	public ResponseEntity<SimpleResponseStructure<AwlogLoggerResponse>> getLogById(@PathVariable("id") Long id)
			throws CustomBaseException {
		logger.debug("Trying get log with id:" + id);
		SimpleResponseStructure<AwlogLoggerResponse> response = new SimpleResponseStructure<>();

		response.setData(AwlogLoggerResponse.fromEntity(awlogLoggerService.getLogById(id)));
		return ResponseEntity.ok(response);
	}

}
