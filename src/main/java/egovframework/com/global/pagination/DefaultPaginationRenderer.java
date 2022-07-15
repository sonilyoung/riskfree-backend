package egovframework.com.global.pagination;

public class DefaultPaginationRenderer extends AbstractPaginationRenderer {

	public DefaultPaginationRenderer() {
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[처음]</a>&#160;";
		previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[이전]</a>&#160;";
		currentPageLabel = "<strong>{0}</strong>&#160;";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
		nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[다음]</a>&#160;";
		lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[마지막]</a>&#160;";
	}

	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {

		return super.renderPagination(paginationInfo, jsFunction);
	}

}
