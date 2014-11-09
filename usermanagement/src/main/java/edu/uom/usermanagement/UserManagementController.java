package edu.uom.usermanagement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.uom.usermanagement.busobject.AddUserModel;
import edu.uom.usermanagement.busobject.AddUserModelResponse;

@RestController
public class UserManagementController {

	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public AddUserModelResponse addUser(AddUserModel model) {
		
		final AddUserModelResponse response = new AddUserModelResponse();
		
		
		return response;
	}
}
