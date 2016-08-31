package com.amazon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.amazon.cart1.dao.CategoryDAO;
import com.amazon.cart1.dao.ProductDAO;
import com.amazon.cart1.dao.SupplierDAO;
import com.amazon.cart1.model.Category;
import com.amazon.cart1.model.Product;
import com.amazon.cart1.model.Supplier;

@Controller
public class AdminController {
	@Autowired
	private Product product;
	@Autowired
	private Category category;
	@Autowired
	private Supplier supplier;
@Autowired
private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private SupplierDAO supplierDAO;
	
	@RequestMapping("/manageCategories")
	public ModelAndView categories()
	{
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("category",category);
		mv.addObject("is admin clicked categories", "true");
	mv.addObject("categoryList",categoryDAO.list());
	return mv;
	}
	@RequestMapping("/manageProducts")
	public ModelAndView products()
	{
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("product",product);
		mv.addObject("is admin clicked products", "true");
	mv.addObject("productList",productDAO.list());
	return mv;
	}
	@RequestMapping("/manageSuppliers")
	public ModelAndView suppliers()
	{
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("supplier",supplier);
		mv.addObject("is admin clicked suppliers", "true");
	mv.addObject("supplierList",supplierDAO.list());
	return mv;
	}
}
