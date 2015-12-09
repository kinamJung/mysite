package com.hanains.mysite.http.action.user;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;
import com.hanains.mysite.http.action.main.IndexAction;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {

		Action action = null;

		if (actionName.equals("joinform")) {
			action = new JoingFormAction();

		} else if (actionName.equals("join")) {
			action = new JoinAction();
		} else if (actionName.equals("joinsuccess")) {
			action = new JoinSuccessAction();
		}else if (actionName.equals("loginForm")) {
			action = new LoginFormAction();
		}else if (actionName.equals("login")) {
			action = new LoginAction();
		}else if (actionName.equals("logout")) {
			action = new LogoutAction();
		}else {
			action = new IndexAction();
		}

		return action;
	}

}
