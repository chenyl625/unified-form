package com.jfbrother.baseserver.utils;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfoConvent<T> {

    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private int pages;
    private int totalCount;
    private List<T> list;

    public PageInfoConvent(int pageNum, int pageSize, List<T> totalList) {
        this.pageNum = pageNum <= 1 ? 1 : pageNum;
        this.pageSize = pageSize <= 1 ? 1 : pageSize;
        if (CollectionUtils.isEmpty(totalList)) {
            list = new ArrayList<>();
            return;
        }
        this.totalCount = totalList.size();
        if (totalCount <= pageSize) {
            this.pages = 1;
        } else {
            if (totalCount % pageSize > 0) {
                this.pages = totalCount / pageSize + 1;
            } else {
                this.pages = totalCount / pageSize;
            }
        }
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
        this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
        if (endRow > totalCount) {
            endRow = totalCount;
        }
        list = totalList.subList(this.startRow, this.endRow);
    }

}
