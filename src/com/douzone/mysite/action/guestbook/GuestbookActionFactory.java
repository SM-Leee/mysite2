package com.douzone.mysite.action.guestbook;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.main.IndexAction;

public class GuestbookActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new ListAction();
		} else if("insert".equals(actionName)) {
			action = new ListInsertAction();
		} else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if("select".equals(actionName)) {
			action = new ListSelectAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("ajax".equals(actionName)) {
			action = new AjaxAction();
		} else if("ajax-list".equals(actionName)) {
			action = new AjaxListAction();
		} else if("ajax-insert".equals(actionName)) {
			action = new AjaxInsertAction();
		} else if("ajax-delete".equals(actionName)) {
			action = new AjaxDeleteAction();
		} else {
			action = new IndexAction();
		}

		return action;
	}

}
