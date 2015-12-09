package com.hanains.mysite.http.action.guestbook;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	private static final String ADD_ACTION = "add";
	private static final String DELETE_ACTION = "delete";
	private static final String DELETE_FORM_ACTION = "deleteForm";

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if (actionName == null) {
			action = new IndexAction();
		} else if (actionName.equals(ADD_ACTION)) {
			action = new AddAction();
		} else if (actionName.equals(DELETE_ACTION)) {
			action = new DeleteAction();
		} else if (actionName.equals(DELETE_FORM_ACTION)) {
			action = new DeleteFormAction();
		} else {
			action = new IndexAction();
		}

		return action;
	}
}
