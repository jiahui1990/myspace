package javacommon.base.model;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class WriteExcel {
	private static Object invokeAttribute(Object owner, String methodName) throws Exception {  
        Class ownerClass = owner.getClass();  
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);  
        Method method = null;  
        method = ownerClass.getMethod("get" + methodName);
        return method.invoke(owner);
    }
	/**
	 * 
	 * @author QiuYuan
	 * @Description: TODO(用一句话描述该方法的作用)
	 * @param title
	 * @param array
	 * @param sheetName
	 * @param os
	 */
	public static void export(String title,JSONArray array,String sheetName,OutputStream os) {
		try {
			//创建Excel工作薄
			HSSFWorkbook book = new HSSFWorkbook();
			//在Excel工作薄中建一张工作表
			HSSFSheet sheet = book.createSheet(sheetName);
			//设置单元格格式(文本)
			String[] titles = title.split(",");
			HSSFRow row = sheet.createRow(0);//创建第一行
			for (int i=0; i < titles.length; i++) {
				row.createCell(i);
				HSSFCell cell = row.createCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(titles[i]);
			}
			//第一行为标题行
			int nums = 0;
			for (int ai=0; ai < array.size(); ai ++) {
				Class cls = array.get(ai).getClass();
				Field[] flds = cls.getDeclaredFields();
				HSSFRow rowi = sheet.createRow(ai + 1);
				for (int i=0; i < flds.length ; i ++) {
					Object obj = invokeAttribute(array.get(ai),flds[i].getName());
					rowi.createCell(i).setCellValue(obj.toString());
				}
			}
			book.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void export(String sheetName, Object object) throws Exception {
		List<Object> list = (List<Object>) object;
		for (Object obj : list) {
			Class cl = obj.getClass();
			Field[] fld = cl.getDeclaredFields();
			for (Field field : fld) {
				String methodName = field.getName().substring(0, 1).toUpperCase()+ field.getName().substring(1);
				Method method = null;
				method = cl.getMethod("get" + methodName);
				Object meString = method.invoke(obj);
				Class c = Class.forName(meString.toString());
				Field[] fds = c.getDeclaredFields();
				if(fds.length > 0) {
					System.out.println(field.getName() + "=" + fds.getClass());
				} else {
					System.out.println(field.getName() + "=" + meString);
				}
			}
			break;
		}
	}
	
	public static void doopen(JSONArray jsar,OutputStream os) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			int i = jsar.size();
			HSSFRow row = sheet.createRow(0);// 创建第一行
			boolean isflag = true;
			for (int j = 0; j < i; j++) {
				JSONObject js = (JSONObject) jsar.get(j);
				String value = js.get("name").toString();
				HSSFCell cell0 = row.createCell(j);
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell0.setCellValue(value);
				JSONArray array = JSONArray.parseArray(js.get("value").toString());
				if (isflag) {
					isflag = false;
					for (int k = 0; k < array.size(); k++) {
						sheet.createRow(k + 1);// 创建第一行
					}
				}
				for (int k = 0; k < array.size(); k++) {
					HSSFCell cell = sheet.getRow(k + 1).createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(array.getString(k));
				}
			}
			workbook.write(os);
			os.flush();
			os.close();
			//JOptionPane.showMessageDialog(null, "导出数据成功！");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/*public static void doopen(JSONArray jsar, String xlsName) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			int i = jsar.size();
			HSSFRow row = sheet.createRow(0);// 创建第一行
			boolean isflag = true;
			for (int j = 0; j < i; j++) {
				JSONObject js = (JSONObject) jsar.get(j);
				String value = js.get("name").toString();
				HSSFCell cell0 = row.createCell(j);
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell0.setCellValue(value);
				JSONArray array = JSONArray.parseArray(js.get("value")
						.toString());
				if (isflag) {
					isflag = false;
					for (int k = 0; k < array.size(); k++) {
						sheet.createRow(k + 1);// 创建第一行
					}
				}
				for (int k = 0; k < array.size(); k++) {

					HSSFCell cell = sheet.getRow(k + 1).createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(array.getString(k));
					System.out.println(-j + ":" + array.getString(k));
				}
			}
			FileOutputStream fOut = new FileOutputStream(xlsName);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
			JOptionPane.showMessageDialog(null, "导出数据成功！");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		jso.put("name", "姓名");
		JSONArray array1 = new JSONArray();
		array1.add("张三");
		array1.add("李四");
		jso.put("value", array1);
		js.add(jso);
		JSONObject jso1 = new JSONObject();
		jso1.put("name", "操作");
		JSONArray array2 = new JSONArray();
		array2.add("查询");
		array2.add("删除");
		jso1.put("value", array2);
		js.add(jso1);
		JSONObject jso2 = new JSONObject();
		jso2.put("name", "返回值");
		JSONArray array3 = new JSONArray();
		array3.add("成功");
		array3.add("失败");
		jso2.put("value", array3);
		js.add(jso2);
		doopen(js, "D:\\workbook.xls");
	}*/
}
