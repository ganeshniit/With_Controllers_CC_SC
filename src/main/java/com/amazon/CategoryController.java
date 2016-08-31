package com.amazon;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amazon.cart1.dao.CategoryDAO;
import com.amazon.cart1.model.Category;

public class CategoryController {
	//private static Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@RequestMapping(value="/categories" , method=RequestMethod.GET)
	public String listCategories(Model model)
	{
		model.addAttribute("category", category);
		model.addAttribute("categoryList", this.categoryDAO.list());
		//log.debug("end of the method listCategories");
		return "category";
	}
	@RequestMapping(value="/category/add", method = RequestMethod.POST)
	
	public String addCategory(@ModelAttribute("category") Category category)
	{
		//log.debug("starting of the method addCategory");
		categoryDAO.saveOrUpdate(category);
		//log.debug("ending of the method addCategory");
		return "category";
	}
	
	@RequestMapping("category/remove/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") String id)
	{
		boolean flag = categoryDAO.delete(id);
		ModelAndView mv = new ModelAndView("category");
		String msg = "successfullydone the operation";
		if(flag!=true)
		{
			msg="the operation was not success";
			
		}
		mv.addObject("msg", msg);
		return mv;
	}
	
	
	
	
	
	

}
