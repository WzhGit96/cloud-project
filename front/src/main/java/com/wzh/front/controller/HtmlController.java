/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

	@RequestMapping("/")
	public String toIndex() {
		return "pages/index";
	}

}
