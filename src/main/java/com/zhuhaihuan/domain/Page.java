package com.zhuhaihuan.domain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 /**
 * �Է�ҳ�Ļ������ݽ���һ���򵥵ķ�װ
 */
public class Page<T> {
 
    private int pageNo = 1;//ҳ�룬Ĭ���ǵ�һҳ
    private int pageSize = 15;//ÿҳ��ʾ�ļ�¼����Ĭ����15
    private int totalRecord;//�ܼ�¼��
    private int totalPage;//��ҳ��
    private List<T> results;//��Ӧ�ĵ�ǰҳ��¼
    private Map<String, Object> params = new HashMap<String, Object>();//�����Ĳ������ǰ�����װ��һ��Map����
    
    public int getPageNo() {
       return pageNo;
    }
 
    public void setPageNo(int pageNo) {
       this.pageNo = pageNo;
    }
 
    public int getPageSize() {
       return pageSize;
    }
 
    public void setPageSize(int pageSize) {
       this.pageSize = pageSize;
    }
 
    public int getTotalRecord() {
       return totalRecord;
    }
 
    public void setTotalRecord(int totalRecord) {
       this.totalRecord = totalRecord;
       //��������ҳ����ʱ��������Ӧ����ҳ�������������Ŀ�����мӷ�ӵ�и��ߵ����ȼ������������Բ������š�
       int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
       this.setTotalPage(totalPage);
    }
 
    public int getTotalPage() {
       return totalPage;
    }
 
    public void setTotalPage(int totalPage) {
       this.totalPage = totalPage;
    }
 
    public List<T> getResults() {
       return results;
    }
 
    public void setResults(List<T> results) {
       this.results = results;
    }
   
    public Map<String, Object> getParams() {
       return params;
    }
   
    public void setParams(Map<String, Object> params) {
       this.params = params;
    }
    public boolean hasPreviousPage(){
    	if(pageNo <= 1 ){
    		return false;
    	}
    	return true;
    }
    public boolean hasNextPage(){
    	if(pageNo >  totalPage-1){
    		return false;
    	}
    	return true;
    }
    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
       builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")
              .append(pageSize).append(", results=").append(results).append(
                     ", totalPage=").append(totalPage).append(
                     ", totalRecord=").append(totalRecord).append("]");
       return builder.toString();
    }
 
}