package egovframework.com.global.common.domain;

import java.io.Serializable;

/**
 * 달력 날짜 모델 클래스
 * 
 * @author TBS suji.h
 * @since 2018.07.19
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2018.07.19  suji.h          최초 생성
 *
 *      </pre>
 */
public class CalendarDateVO implements Serializable {

    private static final long serialVersionUID = -8509545779844669658L;

    /*
     * 년
     */
    private String year = "";

    /*
     * 월
     */
    private String month = "";

    /*
     * 일
     */
    private String day = "";

    /*
     * 날짜
     */
    private String date = "";

    /*
     * 휴일여부
     */
    private String restdeAt = "";

    /*
     * 달력셀
     */
    private int cellNum = 0;

    /*
     * 월별 주순위
     */
    private int weeks = 0;

    /*
     * 월 주수
     */
    private int maxWeeks = 0;

    /*
     * 요일
     */
    private int week = 0;

    /*
     * 시작요일
     */
    private int startWeekMonth = 0;

    /*
     * 마지막 일자
     */
    private int lastDayMonth = 0;

    /*
     * 검색할 정보
     */
    private String searchData = "";

    /*
     * 검색조건
     */
    private String searchCondition = "";

    /*
     * 검색사용여부
     */
    private String searchUseYn = "";

    /**
     * year attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getYear() {
        return year;
    }

    /**
     * year attribute 값을 설정한다.
     * 
     * @param year String
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * month attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getMonth() {
        return month;
    }

    /**
     * month attribute 값을 설정한다.
     * 
     * @param month String
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * day attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getDay() {
        return day;
    }

    /**
     * day attribute 값을 설정한다.
     * 
     * @param day String
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * date attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * date attribute 값을 설정한다.
     * 
     * @param date String
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * restdeAt attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getRestdeAt() {
        return restdeAt;
    }

    /**
     * restdeAt attribute 값을 설정한다.
     * 
     * @param restdeAt String
     */
    public void setRestdeAt(String restdeAt) {
        this.restdeAt = restdeAt;
    }

    /**
     * cellNum attribute 를 리턴한다.
     * 
     * @return int
     */
    public int getCellNum() {
        return cellNum;
    }

    /**
     * cellNum attribute 값을 설정한다.
     * 
     * @param cellNum int
     */
    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    /**
     * weeks attribute 를 리턴한다.
     * 
     * @return int
     */
    public int getWeeks() {
        return weeks;
    }

    /**
     * weeks attribute 값을 설정한다.
     * 
     * @param weeks int
     */
    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    /**
     * maxWeeks attribute 를 리턴한다.
     * 
     * @return int
     */
    public int getMaxWeeks() {
        return maxWeeks;
    }

    /**
     * maxWeeks attribute 값을 설정한다.
     * 
     * @param maxWeeks int
     */
    public void setMaxWeeks(int maxWeeks) {
        this.maxWeeks = maxWeeks;
    }

    /**
     * week attribute 를 리턴한다.
     * 
     * @return int
     */
    public int getWeek() {
        return week;
    }

    /**
     * week attribute 값을 설정한다.
     * 
     * @param week int
     */
    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * startWeekMonth attribute 를 리턴한다.
     * 
     * @return int
     */
    public int getStartWeekMonth() {
        return startWeekMonth;
    }

    /**
     * startWeekMonth attribute 값을 설정한다.
     * 
     * @param startWeekMonth int
     */
    public void setStartWeekMonth(int startWeekMonth) {
        this.startWeekMonth = startWeekMonth;
    }

    /**
     * lastDayMonth attribute 를 리턴한다.
     * 
     * @return int
     */
    public int getLastDayMonth() {
        return lastDayMonth;
    }

    /**
     * lastDayMonth attribute 값을 설정한다.
     * 
     * @param lastDayMonth int
     */
    public void setLastDayMonth(int lastDayMonth) {
        this.lastDayMonth = lastDayMonth;
    }

    /**
     * searchData attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getSearchData() {
        return searchData;
    }

    /**
     * searchData attribute 값을 설정한다.
     * 
     * @param searchData String
     */
    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }

    /**
     * searchCondition attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getSearchCondition() {
        return searchCondition;
    }

    /**
     * searchCondition attribute 값을 설정한다.
     * 
     * @param searchCondition String
     */
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    /**
     * searchUseYn attribute 를 리턴한다.
     * 
     * @return String
     */
    public String getSearchUseYn() {
        return searchUseYn;
    }

    /**
     * searchUseYn attribute 값을 설정한다.
     * 
     * @param searchUseYn String
     */
    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }


}
