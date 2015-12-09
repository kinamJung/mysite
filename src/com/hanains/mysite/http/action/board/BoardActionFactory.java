package com.hanains.mysite.http.action.board;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if (actionName == null) {
			action = new ListAction();
		} else if (actionName.equals("writeForm")) {
			action = new WriteFormAction();
		} else if (actionName.equals("write")) {
			action = new WriteAction();
		}else {
			action = new ListAction();
		}
		
		return action;
	}

}
