package com.scu.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.scu.dao.BaseDAO;
import com.scu.dto.PlayDetailDto;
import com.scu.utils.Consts;

@Transactional
@Controller
public class BaseService<M extends java.io.Serializable>{

	protected BaseDAO<M> baseDAO;
	
	public List executeSql(String sql, Class clazz, final Object ... objects){
		return this.baseDAO.createSqlQueryWithClass(sql,clazz,objects);
	}
	
	public List executeSql(String sql, final Object ... objects){
		return this.baseDAO.createSqlQuery(sql,objects);
	}
	
	public void setDAO(BaseDAO<M> dao){
		this.baseDAO = dao;
	}
	
	public M findById(int id) {
		return baseDAO.findById(id);
	}

	public List<M> findByExample(M example) {
		return baseDAO.findByExample(example);
	}
	
	public void deleteById(int id) {
		baseDAO.deleteById(id);
	}

	public void deleteObject(M model) {
		baseDAO.delete(model);
	}

	public List<M> findAll() {
		return baseDAO.findAll();
	}

	public void merge(M model) {
		
	}

	public void saveOrUpdate(M model) {
		baseDAO.saveOrUpdate(model);
		//throw new RuntimeException("事务异常测试"); 
	}

	public void update(M model) {
		baseDAO.saveOrUpdate(model);
	}

	/*public Page<M> getPage(M model,int currentPage) {
		int pageSize = Consts.DEFAULT_PAGE_SIZE;
		int totalCount = baseDAO.getTotalCount(model);
		int totalPage = PageUtil.getTotalPage(totalCount, pageSize);
		currentPage = PageUtil.handleCurrentPage(currentPage, totalPage);
		int firstIndex = PageUtil.getFirstIndex(currentPage, pageSize);
		int maxIndex = pageSize;
		List<M> items = baseDAO.getPage(model, firstIndex, maxIndex);
		Page<M> page = new Page<M>(items,totalCount, pageSize, currentPage);
		return page;
	}*/
	
	public List<M> findListByPage( int from, int length,Criterion ... criterion){
		return baseDAO.getPage(from, length, criterion);
	}

	public int getTotalCount(Criterion ... criterion) {
		return baseDAO.getTotalCount(criterion);
	};
	
	/*public boolean printData(List<Out> list){
		boolean bool = true;
		//创建�?��webbook，对应一个Excel文件
		String path=ServletActionContext.getServletContext().getRealPath("/document/备件出库�?xls");
		InputStream is = null;
		HSSFWorkbook wb = null;
		try {
			is = new FileInputStream(path);
		    wb = new HSSFWorkbook(is);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		
		for (int i=0,j=0; i < list.size(); i++,j++) {
				row = sheet.createRow(i + 3);
				Out out = (Out) list.get(i);
				// 第四步，获取单元格，并设置�?
				cell = row.createCell(0);
				cell.setCellValue(out.getIn().getInNum());
				cell = row.createCell(1);
				cell.setCellValue(out.getOutNum());
				cell = row.createCell(2);
				cell.setCellValue(out.getBakAmountOut());
				cell = row.createCell(3);
				cell.setCellValue(out.getBakOutPrice());
				cell = row.createCell(4);
				cell.setCellValue(out.getOutType());
				cell = row.createCell(5);
				cell.setCellValue(StringUtil.formatDateAsYmdyms(out.getOutTime()));
		}
		
		try
		{	// 将文件存到指定位�?
			String fileName = ServletActionContext.getServletContext().getRealPath("/document/temp/ckd.xls");
			FileOutputStream fout = new FileOutputStream(fileName);
			wb.write(fout);
			fout.close();
			
			//打印机打�?
			try {
				Desktop.getDesktop().print(new File(fileName));
			} catch (IOException e) {
				bool=false;
				e.printStackTrace();
			}
			
			if(!StringUtil.print("Excel.Application", "Workbooks", fileName)) {
				bool=false;
			}
		}
		catch (Exception e)
		{
			bool=false;
			e.printStackTrace();
		}
		return bool;
	}*/
}
