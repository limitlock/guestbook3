package com.cafe24.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.guestbook.dao.GuestbookDao;
import com.cafe24.gusetbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	@Autowired
	private GuestbookDao guestbookDao;

	@RequestMapping("/list")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookDao.getList();
		model.addAttribute("list", list);

		return "/WEB-INF/views/list.jsp";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		guestbookDao.insert(vo);
		return "redirect:/list";
	}

	@RequestMapping(value = "/deleteform", method = RequestMethod.GET)
	public String delete() {

		return "/WEB-INF/views/deleteform.jsp";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo) {
		guestbookDao.delete(vo);
		return "redirect:/list";
	}

}
