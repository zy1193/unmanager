/**
 * 说明：日期选择组件
 * 作者：张彦
 * 日期：2004-6-4
 */

var Calendar_Separator = "-";							// 日期分隔符

var Calendar_BeginYear = 1980;							// 年份选择的起始
var Calendar_EndYear = 2020;							// 年份选择的结束

var Calendar_ShowDateTip = true;						// 当鼠标悬停时，是否显示日期提示

var Calendar_BackgroundColor = "#FFFFFF";				// 日历的背景色
var Calendar_BorderColor = "#808080";					// 日历框架边框色
var Calendar_WeekCellColor = "#C0C0C0";					// 星期名称前景色
var Calendar_WeekCellBackgroundColor = "#808080";		// 星期名称背景色
var Calendar_CellBorderColor = "#808080";				// 日期单元格边框色
var Calendar_CellHoverColor = "#000000";				// 日期单元格在鼠标悬停时的前景色
var Calendar_CellHoverBackgroundColor = "#C0C0C0";		// 日期单元格在鼠标悬停时的背景色
var Calendar_CellColor = "#000000";						// 日期单元格前景色
var Calendar_TodayCellColor = "#FF0000";				// 日历中当天日期单元格的前景色
var Calendar_NavBarColor = "#FF0000";					// 导航栏前景色
var Calendar_NavBarBackgroundColor = "#FFFFFF";			// 导航栏背景色

var Calendar_CellPreColor = null;						// 日期单元格在鼠标悬停前的前景色
var Calendar_CellPreBackgroundColor = null;				// 日期单元格在鼠标悬停前的背景色

var Calendar_Date = new Date();							// 日历的当天日期

var Calendar_Text = null;								// 选中的日期，填写到该文本框

// 创建日历框架
function createCalendarFrame() {

	var i;
	var week = new Array("日","一","二","三","四","五","六");
	var month = new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");

	with (document) {

		// 日历框架
		write("<div id='Calendar_Frame' style='position:absolute;top:100;left:100;width:155;height:188;display:none;border:1px solid " + Calendar_BorderColor + "'>");
		write("<iframe frameborder='0' height='100%' width='100%'></iframe>");
		write("<div style='position:absolute;top:1;left:1;width:151;height:184'>");

		// 年月下拉列表
		write("<div align='center'>");
		write("<select id='Calendar_YearList' onchange='selectChange()' style='width:74'>");
		for (i = Calendar_BeginYear; i <= Calendar_EndYear; i ++) write("<option value='" + i + "'>" + i + "</option>");
		write("</select>");
		write("<select id='Calendar_MonthList' onchange='selectChange()' style='width:74'>");
		for (i = 0; i < 12; i ++) write("<option value='" + i + "'>" + month[i] + "</option>");
		write("</select>");
		write("</div>");

		// 日期表格
		write("<table width='147' style='font-family:verdana,宋体;font-size:11px;vertical-align:middle;text-align:center;background-color:" + Calendar_BackgroundColor + "' align='center' border='0' cellpadding='0' cellspacing='0'>");

		// 星期名称
		write("<tr height='20' style='color:" + Calendar_WeekCellColor + ";background-color:" + Calendar_WeekCellBackgroundColor + ";cursor:default'>");
		for (i = 0; i < 7; i ++) write("<td width='21'>" + week[i] + "</td>");
		write("</tr>");

		// 当月所有日期
		var d = 0,j = 0;
		for (i = 0; i < 6; i ++) {
			write("<tr height='20'>");
			for (j = 0; j < 7; j ++) {
				if (d > 36) {
					write("<td></td><td></td><td></td><td></td><td></td>");
					break;
				}
				write("<td style='border:0px solid " + Calendar_CellBorderColor + "' id='Calendar_Day" + d + "' onmouseover='mouseHover(this)' onmouseout='mouseLeave(this)' onclick='pick(this);'></td>");
				d ++;
			}
			write("</tr>");
		}

		// 日历导航及功能按钮栏
		write("<tr height='22' style='font-family:webdings;font-size:9pt;color:" + Calendar_NavBarColor + ";background-color:" + Calendar_NavBarBackgroundColor + "'>");
		write("<td></td>");
		write("<td style='cursor:hand;border:0px solid " + Calendar_CellBorderColor + "' title='上一年' onmouseover='mouseHover(this)' onmouseout='mouseLeave(this)' onclick='calendarNavigate(-12)'>7</td>");
		write("<td style='cursor:hand;border:0px solid " + Calendar_CellBorderColor + "' title='上一月' onmouseover='mouseHover(this)' onmouseout='mouseLeave(this)' onclick='calendarNavigate(-1)'>3</td>");
		write("<td style='cursor:hand;border:0px solid " + Calendar_CellBorderColor + "' title='下一月' onmouseover='mouseHover(this)' onmouseout='mouseLeave(this)' onclick='calendarNavigate(1)'>4</td>");
		write("<td style='cursor:hand;border:0px solid " + Calendar_CellBorderColor + "' title='下一年' onmouseover='mouseHover(this)' onmouseout='mouseLeave(this)' onclick='calendarNavigate(12)'>8</td>");
		write("<td></td>");
		write("<td style='cursor:hand;border:0px solid " + Calendar_CellBorderColor + "' title='关闭' onmouseover='mouseHover(this)' onmouseout='mouseLeave(this)' onclick='hideCalendar()'>r</td>");
		write("</tr>");
		write("</table>");

		write("</div></div>");
	}
}

// 判断年份是否是否闰年(如果年份是1901-2099，那么可以简单的判断年份能否被4整除)
function isLeap(y) {
	// return ((y % 4 == 0) && (y % 100 != 0)) || ((y % 400) == 0);
	return y % 4 == 0;
}

// 取得月份的第一天为星期几
function getFirstDay(y,m) {
	var d = new Date(y,m,1);
	return d.getDay();
}

// 取得月份共有几天
function getMonthSize(y,m) {
	var daysArr = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	var days = daysArr[m];
	if (isLeap(y) && m == 1) days ++;
	return days;
}

// 设置日历
function setCalendar() {
	var y = Calendar_Date.getFullYear();
	document.all["Calendar_YearList"].selectedIndex = y - Calendar_BeginYear;
	var m = Calendar_Date.getMonth();
	document.all["Calendar_MonthList"].selectedIndex = m;
	var d = Calendar_Date.getDate();

	var firstDay = getFirstDay(y,m);
	var monthSize = getMonthSize(y,m);
	var day = 1;
	var td;

	for (var i = 0; i < 37; i ++) {
		td = document.all["Calendar_Day" + i];
		with (td) {
			if (i < firstDay) {
				if (Calendar_ShowDateTip) title = "";
				innerText = "";
				style.cursor = "";
				continue;
			}

			if (day <= monthSize) {
				if (day == d) {
					style.fontWeight = "bold";
					style.color = Calendar_TodayCellColor;
				} else {
					style.fontWeight = "";
					style.color = Calendar_CellColor;
				}
				if (Calendar_ShowDateTip) title = y + Calendar_Separator + (m + 1) + Calendar_Separator + day;
				innerText = day ++;
				style.cursor = "hand";
				continue;
			}

			if (Calendar_ShowDateTip) title = "";
			innerText = "";
			style.cursor = "";
		}
	}
}

// 隐藏日历
function hideCalendar() {
	document.all["Calendar_Frame"].style.display = "none";
}

// 显示日历；当天日期由obj的值设置，位置取决于obj的位置和尺寸，选中的日期写入obj
function showCalendar(obj) {
	if (obj.type == "text") {
		Calendar_Text = obj;
		setDate(obj.value);
	}
	var calendar = document.all["Calendar_Frame"];
	var calendarHeight = 186;
	var top = offsetTop(obj) + obj.offsetHeight + 1;
	if (top + calendarHeight > document.body.scrollTop + document.body.offsetHeight) top -= obj.offsetHeight + calendarHeight + 2;
	calendar.style.pixelLeft = offsetLeft(obj);
	calendar.style.pixelTop = top;
	if (calendar.style.display == "none") calendar.style.display = "";
}

// 更改日期分隔符
function setSeparator(value) {
	Calendar_Separator = value;
}

// 将字符串类型的日期转换为日期类型的日期，非日期字串则返回null
function convStringToDate(str) {
	var reg = /^(\d{4})([-\/\.])(\d{1,2})\2(\d{1,2})$/;
	if (reg.test(str)) {
		var y,m,d;
		with (RegExp) {
			y = parseInt($1,10);
			m = parseInt($3,10) - 1;
			d = parseInt($4,10);
		}
		return new Date(y,m,d);
	} else {
		return null;
	}
}

// 更改日期并重新设置日历
function setDate(date) {
	if (date.constructor == String) date = convStringToDate(date);
	if (date != null && date.getTime() != Calendar_Date.getTime()) {
		Calendar_Date = date;
		setCalendar();
	}
}

// 年月下拉列表被重新选择
function selectChange() {
	var y = parseInt(document.all["Calendar_YearList"].value,10);
	var m = parseInt(document.all["Calendar_MonthList"].value,10);
	var d = Calendar_Date.getDate();
	var monthSize = getMonthSize(y,m);
	var date;
	if (monthSize >= d) {
		date = new Date(y,m,d);
	} else {
		date = new Date(y,m,monthSize);
	}
	setDate(date);
}

// 导航按钮功能；参数为日期变化的月数
function calendarNavigate(month) {
	var y = document.all["Calendar_YearList"].selectedIndex;
	var m = document.all["Calendar_MonthList"].selectedIndex;

	switch (month) {
		case -12 :
			y --; break;
		case -1 :
			m --; break;
		case 1 :
			m ++; break;
		case 12 :
			y ++; break;
		default :
			return;
	}

	if (m == -1) {
		y --;
		m = 11;
	}
	if (m == 12) {
		y ++;
		m = 0;
	}
	
	// 年份范围应该在设定的范围之内
	if (y >= 0 && y <= Calendar_EndYear - Calendar_BeginYear) {
		document.all["Calendar_YearList"].selectedIndex = y;
		document.all["Calendar_MonthList"].selectedIndex = m;
		selectChange();
	}
}

// 鼠标在日期单元格上悬停
function mouseHover(td) {
	with (td) {
		if (innerText != "") {
			// Calendar_CellPreColor = style.color;
			// style.color = Calendar_CellHoverColor;
			Calendar_CellPreBackgroundColor = style.backgroundColor;
			style.backgroundColor = Calendar_CellHoverBackgroundColor;
			style.borderWidth = "1";
		}
	}
}

// 鼠标离开日期单元格
function mouseLeave(td) {
	with (td) {
		if (innerText != "") {
			// style.color = Calendar_CellPreColor;
			style.backgroundColor = Calendar_CellPreBackgroundColor;
			style.borderWidth = "0";
		}
	}
}

// 日期被选择
function pick(td) {
	with (td) {
		if (innerText != "") {
			var y = document.all["Calendar_YearList"].value;
			var m = document.all["Calendar_MonthList"].value;
			var d = innerText;
			if (Calendar_Text.type == "text") {
				if (Calendar_ShowDateTip) {
					Calendar_Text.value = title;
				} else {
					Calendar_Text.value = y + Calendar_Separator + (parseInt(m) + 1) + Calendar_Separator + d;
				}
			}
			hideCalendar();
		}
	}
}

function offsetLeft(obj) {
	var left = 0;
	do {
		left += obj.offsetLeft;
		obj = obj.offsetParent;
	} while (obj != null);
	return left;
}

function offsetTop(obj) {
	var top = 0;
	do {
		top += obj.offsetTop;
		obj = obj.offsetParent;
	} while (obj != null);
	return top;
}

createCalendarFrame();	// 建立日历框架
setCalendar();			// 设置日历内容