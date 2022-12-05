package app.notice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import app.notice.service.NoticeService;
import app.notice.vo.NoticeVO;

@Controller
public class NoticeController {
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	@GetMapping("/notice/enroll")
	public String noticeEnrollPage(HttpSession session) {
		if(session.getAttribute("mbrId") == null) {
			return "redirect:/login";
		}
		
		return "notice/enroll";
	}
	
	@PostMapping("/notice/enroll")
	public String noticeEnroll(NoticeVO noticeVO, HttpSession session) {
		
		// 1. 유효성 검사(제목, 내용)
		if (StringUtils.isEmpty(noticeVO.getNtcTtl()) || StringUtils.isEmpty(noticeVO.getNtcCn())) {
			return "error";
		}
		
		if(session.getAttribute("mbrId") == null) {
			return "redirect:/login";
		}
		
		String mbrId = session.getAttribute("mbrId").toString();
		noticeVO.setRgtrId(mbrId);

		// 2. 서비스 로직 호출
		noticeService.enroll(noticeVO);
		
		
		// 3. 결과응답
		
		return "redirect:/notice/" + noticeVO.getNtcSn();
	}
	
	@PostMapping(value = "notice/enroll", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> noticeEnrollAjax(@RequestBody NoticeVO noticeVO) {
		Map<String, Object> result = new HashMap();
		
		// 1. 유효성 검사(제목, 내용)
		if (StringUtils.isEmpty(noticeVO.getNtcTtl()) || StringUtils.isEmpty(noticeVO.getNtcCn())) {
			result.put("code", 400);
			return result;
		}

		// 2. 서비스 로직 호출
		noticeService.enroll(noticeVO);
		
		
		// 3. 결과응답
		result.put("code", 200);
		result.put("ntcSn", noticeVO.getNtcSn());
		return result;
	}
	
	@GetMapping("/notice/{sn}")
	public String noticeDetail(@PathVariable String sn, ModelMap model) {
		// 1. sn -> ntcSn (String to int)
		int ntcSn = Integer.parseInt(sn);
		
		// 2. 조회 로직 호출(service)
		NoticeVO noticeVO = noticeService.read(ntcSn);
		
		// 3. Model 
		model.addAttribute("noticeVO", noticeVO);
		
		return "notice/detail";
	}
	
	@GetMapping("/notice")
	public String noticeList(@ModelAttribute NoticeVO noticeVO, ModelMap model) {
		
		Map<String, Object> result = noticeService.readAll(noticeVO);
		
		model.addAttribute("noticeList", result.get("noticeList"));
		model.addAttribute("totalRecordCount", result.get("totalRecordCount"));
		model.addAttribute("topFixList", result.get("topFixList"));
		model.addAttribute("paginationInfo", result.get("paginationInfo"));
		
		return "notice/list";
		
	}

}
