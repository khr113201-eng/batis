package com.jdbc.util;

public class MyUtil {//페이징 처리
	
	//전체페이지 갯수
	public int getPageCount(int numPerPage,int dataCount) {
		
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount++;
		}
		
		return pageCount;
		
	}
	
	//페이징 처리 메소드
	public String pageIndexList(int currentPage,int totalPage,
			String listUrl) {
		
		int numPerBlock = 5; //◀이전 6 7 8 9 10 다음▶
		int currentPageSetup;//◀이전
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0) {
			return "";
		}
		
		//list.jsp
		//list.jsp?searchKey=name&searchValue=suzi
		
		if(listUrl.indexOf("?")!=-1) {//검색
			listUrl = listUrl + "&";
		}else {
			listUrl = listUrl + "?";
		}
		
		//◀이전의 pageNum
		//       1 2 3 4 5 다음▶
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;			
		}
		
		//◀이전
		if(totalPage>numPerBlock && currentPageSetup>0) {
			
			sb.append("<a href=\"" + listUrl + "pageNum=" +
					currentPageSetup + "\">◀이전</a>&nbsp;");
			
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp;
		}
		
		//바로가기 페이지   
		//◀이전 6 7 8 9 10 다음▶
		page = currentPageSetup + 1;
		while(page<=totalPage && page<= (currentPageSetup+numPerBlock)) {
			
			if(page==currentPage) {
				sb.append("<font color=\"Fuchsia\">" + page + 
						"</font>&nbsp;");
				//<font color="Fuchsia">9</font>&nbsp;
			}else {
				sb.append("<a href=\"" + listUrl + "pageNum=" +
						page + "\">" + page + "</a>&nbsp;");
				
				//<a href="list.jsp?pageNum=10">10</a>&nbsp;
			}	
			
			page++;
			
		}		
		
		//다음▶
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12
		if(totalPage - currentPageSetup > numPerBlock) {
			
			sb.append("<a href=\"" + listUrl + "pageNum=" +
					page + "\">다음▶</a>&nbsp;"); 
			
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
			
		}
		
		return sb.toString();
		
	}	
	

}



















