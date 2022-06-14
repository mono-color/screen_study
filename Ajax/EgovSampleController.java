/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 *   2009.03.16 최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *      Copyright (C) by MOPAS All right reserved.
 */

@Controller
public class EgovSampleController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@RequestMapping(value = "/test_ajax.do")
	public String test_ajax(HttpServletRequest request) {
		System.out.println("/test_ajax.do");

		String name = request.getParameter("name");
		String lecture = request.getParameter("lecture");
		System.out.println("name::::" + name + ",    lecture::::" + lecture);

		return "sample/test_ajax00.html";
	}

	@RequestMapping(value = "/fn_ajax00")
	public String fn_ajax00() {
		System.out.println("fn_ajax00");
		return "sample/test_ajax01.html";
	}

	@RequestMapping(value = "/fn_ajax01")
	public String fn_ajax01(HttpServletRequest request) {
		System.out.println("fn_ajax01");

		String name = request.getParameter("name");
		String lecture = request.getParameter("lecture");

		System.out.println("name:: " + name);
		System.out.println("lecture:: " + lecture);

		return "sample/test_ajax01.html";
	}

	@RequestMapping(value = "/fn_ajax02")
	public String fn_ajax02(HttpServletRequest request) {
		System.out.println("fn_ajax02");

		String name = request.getParameter("name");
		String lecture = request.getParameter("lecture");

		System.out.println("name:: " + name);
		System.out.println("lecture:: " + lecture);

		return "sample/test_ajax01.html";
	}

	@RequestMapping(value = "/fn_ajax03.do")
	public String fn_ajax03(HttpServletRequest request) {
		System.out.println("/fn_ajax03.do");
		String center_info = request.getParameter("center_info");
		System.out.println("center_info: " + center_info);
		center_info = center_info.replaceAll("&quot;", "\"");
		System.out.println("center_info: " + center_info);
		JSONParser jsonParser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(center_info);
			System.out.println("jsonObject : " + jsonObject);
			System.out.println(jsonObject.get("name"));
			System.out.println(jsonObject.get("lecture"));

			Iterator iterator = jsonObject.keySet().iterator();
			while (iterator.hasNext()) {

				String key = iterator.next().toString(); // key 순차적으로 추출
				System.out.println("key:::" + key + ", value::" + jsonObject.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sample/test_ajax01.html";
	}

	@RequestMapping(value = "/fn_ajax03_1.do")
	public String fn_ajax03_1(@RequestBody Map<String, Object> map) {
		System.out.println("fn_ajax03_1");
		System.out.println("name:: " + map.get("name"));
		System.out.println("lecture:: " + map.get("lecture"));
		return "sample/test_ajax01.html";
	}

	
	@RequestMapping(value="/fn_ajax04.do")
	public String fn_ajax04(HttpServletRequest request) {
		System.out.println("fn_ajax04");
		
		String centerInfoArr = request.getParameter("centerInfoArr");
		System.out.println("centerInfoArr: " +  centerInfoArr);
		centerInfoArr = centerInfoArr.replaceAll("&quot;", "\"");
		System.out.println("RcenterInfoArr: " +  centerInfoArr);
		System.out.println("RcenterInfoArrSize: " +  centerInfoArr.length());
		
		JSONParser jsonParser = new JSONParser();
		try {
			JSONArray jsonArr = (JSONArray) jsonParser.parse(centerInfoArr);
			System.out.println("jsonArr: " + jsonArr);
			System.out.println("jsonArrSize: " + jsonArr.size());
			
			for(int i = 0; i < jsonArr.size(); i++) {
				System.out.println("인덱스 i: "+ i + ", 값: " + jsonArr.get(i));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sample/test_ajax01.html";
	}
	
	@RequestMapping(value="/fn_ajax04_1.do")
	public String fn_ajax04_1(@RequestParam(name = "centerArr[]",required = false)List<String>centerArr) {
		System.out.println("fn_ajax04_1");
		System.out.println(centerArr);
		System.out.println("centerArr.size(): " + centerArr.size());
		for(int i = 0; i<centerArr.size(); i++) {
			System.out.println(centerArr.get(i));
		}
		return "sample/test_ajax01.html";
	}
	
	@RequestMapping(value="/fn_ajax05.do")
	public String fn_ajax05(HttpServletRequest request) {
		System.out.println("fn_ajax05");
		
		String center_lecture = request.getParameter("center_lecture");
		System.out.println("center_lecture : " + center_lecture);

		center_lecture = center_lecture.replace("&quot;", "\"");
		System.out.println("center_lecture_replace : " + center_lecture);
		
		JSONParser jsonParser = new JSONParser();
		
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(center_lecture);
			System.out.println("jsonObject: " + jsonObject);
			
			JSONArray lecture01 = (JSONArray) jsonObject.get("lecture01");
			JSONArray lecture02 = (JSONArray) jsonObject.get("lecture02");
			
			System.out.println("lecture02.size(): " + lecture02.size());
			for(int i=0; i < lecture02.size(); i++) {
				System.out.println(lecture02.get(i));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "sample/test_ajax01.html";
	}
	@RequestMapping(value="/fn_ajax05_1.do")
	public String fn_ajax05_1(@RequestBody Map<String, Object> map) {
		System.out.println("fn_ajax05_1");
		System.out.println(map);
		System.out.println(map.get("lectrue01"));
		
		
		return "sample/test_ajax01.html";
	}
	
	@RequestMapping(value="fn_ajax06.do")
	@ResponseBody
	public String fn_ajax06() {
		System.out.println("fn_ajax06");
		String message = "안녕하세요 넥스트 입니다.";
		return message;
	}
	@RequestMapping(value="fn_ajax07.do")
	@ResponseBody
	public String fn_ajax07() {
		System.out.println("fn_ajax07");
		
		//Java Object to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String send_data = "";
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("center_no", 8850);
		map.put("name", "NextIT");
		map.put("lecture", "화면구현");
		map.put("region", "대전");
		
		try {
			send_data = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(send_data);

		return send_data;
	}
	
	@RequestMapping(value="fn_ajax08.do")
	@ResponseBody
	public Map<String, Object> fn_ajax08() {
		System.out.println("fn_ajax08");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("center_no", 8850);
		resultMap.put("name", "NextIT");
		resultMap.put("lecture", "화면구현");
		resultMap.put("region", "Daejeon");
				
		return resultMap;
	}
	
	@RequestMapping(value="/test_ajax02.do")
	public String test_ajax02() {
		System.out.println("test_ajax02");
		return "sample/test_ajax02.html";
	}
	
	/**
	 * 글 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */

	@RequestMapping(value = "/login.do")
	public String login() {
		System.out.println("/login.do");
		return "sample/login.html";
	}

	@RequestMapping(value = "/test_ajax01.do")
	public String test_ajax01() {
		System.out.println("/test_ajax01.do");
		return "sample/test_ajax01.html";
	}

//	@RequestMapping(value = "/fn_ajax01.do")
//	public String fn_ajax01(HttpServletRequest request) {
//		System.out.println("/fn_ajax01.do");
//		String name = request.getParameter("name");
//		String lecture = request.getParameter("lecture");
//		System.out.println("name::::" + name + ",    lecture::::" + lecture);
//		return "sample/test_ajax01.html";
//	}

	@RequestMapping(value = "/egovSampleList.do")
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> sampleList = sampleService.selectSampleList(searchVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "sample/egovSampleList";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * 
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.GET)
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 등록한다.
	 * 
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.POST)
	public String addSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status) throws Exception {

		// Server-Side Validation
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		sampleService.insertSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글 수정화면을 조회한다.
	 * 
	 * @param id       - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		model.addAttribute(selectSample(sampleVO, searchVO));
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 조회한다.
	 * 
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO)
			throws Exception {
		return sampleService.selectSample(sampleVO);
	}

	/**
	 * 글을 수정한다.
	 * 
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateSample.do")
	public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status) throws Exception {

		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		sampleService.updateSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글을 삭제한다.
	 * 
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteSample.do")
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO,
			SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	@RequestMapping(value = "/test_map01.do")
	public String test_map01() {
		System.out.println("/test_map01.do");
		return "sample/test_map01.html";
	}

}
