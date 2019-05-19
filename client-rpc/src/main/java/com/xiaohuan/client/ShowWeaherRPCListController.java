package com.xiaohuan.client;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("Weather")
public class ShowWeaherRPCListController {
	@Autowired
	private CallWeatherRPC weatherRpc;

	@ResponseBody
	@RequestMapping(value = "/ListAllweather.action", method = { RequestMethod.POST })
	public Object ListAllWeather(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		return JSON.toJSON(WatchWeather.weatherlist);
	}

	@ResponseBody
	@RequestMapping(value = "/CallWeatherRPC.action", method = { RequestMethod.POST })
	public Object CallWeatherRPC(@RequestParam(required = false) String ip, @RequestParam(required = false) Integer port, String city, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		//CallWeatherRPC weatherRpc = new CallWeatherRPC();
		if((ip == null || "".equals(ip)) && (port == null)){
			return JSON.toJSON(weatherRpc.callWeather(city));
		}else {
			return JSON.toJSON(weatherRpc.callWeather(ip, port, city));
		}
	}


}
