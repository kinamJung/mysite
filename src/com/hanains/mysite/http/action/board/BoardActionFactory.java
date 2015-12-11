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
		} else if (actionName.equals("boardView")) {
			action = new BoardViewAction();
		} else if (actionName.equals("delete")) {
			action = new DeleteAction();
		} else if (actionName.equals("displayPaging")) {
			action = new DisplayPagingAction();
		} else if (actionName.equals("search")) {
			action = new SearchAction();
		} else if (actionName.equals("modifyForm")) {
			action = new ModifyForm();
		} else if (actionName.equals("modify")) {
			action = new ModifyAction();
		}

		else {
			action = new ListAction();
		}

		return action;
	}

}
