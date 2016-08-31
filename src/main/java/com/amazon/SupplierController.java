package com.amazon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazon.cart1.dao.SupplierDAO;
import com.amazon.cart1.model.Supplier;

public class SupplierController {
	
	@Autowired
	//You can use @Autowired annotation on setter methods to get rid of the <property> element in 
	//XML configuration file. When Spring finds an @Autowired annotation used with setter 
	//methods, it tries to perform byType autowiring on the method.
	private SupplierDAO supplierDAO;

	
	@RequestMapping(value="/suppliers", method=RequestMethod.GET)
	public String listSuppliers(Model model)
	{
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("supplierList", this.supplierDAO.list());
		return "supplier";
	}
	
	//For add and update supplier both
	@RequestMapping(value = "/supplier/add", method = RequestMethod.POST)
	public String addSupplier(@ModelAttribute("supplier") Supplier supplier)
	{
		supplierDAO.saveOrUpdate(supplier);
		return "redirect:/suppliers";
	}
	
	@RequestMapping("supplier/remove/{id}")
	public String removeSupplier(@PathVariable("id") String id,ModelMap model)
	{
		supplierDAO.delete(id);
		model.addAttribute("message", "Added Successfully");
		return "redirect:/suppliers";
		
	}
	@RequestMapping("supplier/edit/{id}")
	public String editSupplier(@PathVariable("id") String id,Model model)
	{
		System.out.println("editSupplier");
		model.addAttribute("supplier", this.supplierDAO.get(id));
		model.addAttribute("listSuppliers", this.supplierDAO.list());
		return "supplier";
		
	}
}
