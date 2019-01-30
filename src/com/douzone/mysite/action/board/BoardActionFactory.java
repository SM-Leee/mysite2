package com.douzone.mysite.action.board;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.main.IndexAction;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new ListAction();
		} else if("select".equals(actionName)) {
			action = new ListSelectAction();
		} else if("writeAdd".equals(actionName)) {
			action = new WriteAction();
		} else if("write".equals(actionName)) {
			action = new InsertAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("replyView".equals(actionName)) {
			action = new ReplyViewAction();
		} else if("reply".equals(actionName)) {
			action = new ReplyAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("modifyForm".equals(actionName)) {
			action = new ModifyFormAction();
		} else if("searchselect".equals(actionName)) {
			action = new SearchSelect();
		} else if("deletecomment".equals(actionName)) {
			action = new DeleteCommentAction();
		} else if(("insertcomment").equals(actionName)) {
			action = new InsertCommentAction();
		}
		else {
			action = new IndexAction();
		}
		
		return action;
	}

}
