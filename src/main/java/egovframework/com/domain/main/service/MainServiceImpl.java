package egovframework.com.domain.main.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.domain.main.dao.MainDAO;
import egovframework.com.domain.main.domain.AccidentsAmount;
import egovframework.com.domain.main.domain.Amount;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.Company;
import egovframework.com.domain.main.domain.EssentialInfo;
import egovframework.com.domain.main.domain.EssentialRate;
import egovframework.com.domain.main.domain.ExcelTitleType;
import egovframework.com.domain.main.domain.Graph;
import egovframework.com.domain.main.domain.Improvement;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.Notice;
import egovframework.com.domain.main.domain.ParamDutyCyle;
import egovframework.com.domain.main.domain.ParamMainExcelData;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.domain.PramAmount;
import egovframework.com.domain.main.domain.Report;
import egovframework.com.domain.main.domain.SafeWork;
import egovframework.com.domain.main.domain.Series;
import egovframework.com.domain.main.domain.Setting;
import egovframework.com.domain.main.domain.Weather;
import egovframework.com.domain.main.domain.WeatherInfo;
import egovframework.com.domain.main.domain.Workplace;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.relatedlaw.dao.RelatedLawDAO;
import egovframework.com.domain.relatedlaw.domain.RelatedLaw;
import egovframework.com.global.common.domain.GlobalsProperties;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainDAO repository;
	
	@Autowired
	private RelatedLawDAO rlRepository;	
	

	@Override
	public List<Company> getScaleInfo(Company vo) {
		// TODO Auto-generated method stub
		return repository.getScaleInfo(vo);
	}
	

	@Override
	public List<Company> getSectorInfo(Company vo) {
		// TODO Auto-generated method stub
		return repository.getSectorInfo(vo);
	}	
	
	@Override
	public Company getCompanyInfo(Company vo) {
		return repository.getCompanyInfo(vo);
	}

	@Override
	public List<Workplace> getWorkplaceList(Workplace vo) {
		if("001".equals(vo.getRoleCd())) {//대표이사
			int cnt = repository.getWorkplaceCount(vo);
			if(cnt > 0) {
				return repository.getWorkplaceList(vo);
			}else {
				return repository.getWorkplaceRoleList(vo);
			}
		}else {
			int cnt = repository.getCeoWorkplaceCount(vo);
			if(cnt < 1) {
				return repository.getWorkplaceRoleWorkerList(vo);
			}else {
				return repository.getWorkplaceList(vo);	
			}
		}
	}
	
	@Override
	public List<Workplace> getMyWorkplace(Workplace vo) {
		return repository.getMyWorkplace(vo);
	}	
	
	@Override
	public Baseline getRecentBaseline(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getRecentBaseline(vo);
	}		
	
	@Override
	public Baseline getBaseline(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getBaseline(vo);
	}	

	@Override
	public List<Baseline> getBaselineList(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getBaselineList(vo);
	}

	@Override
	public List<Notice> getNoticeHotList(Notice vo) {
		// TODO Auto-generated method stub
		return repository.getNoticeHotList(vo);
	}	
	
	@Override
	public List<Notice> getNoticeList(Notice vo) {
		// TODO Auto-generated method stub
		return repository.getNoticeList(vo);
	}

	@Override
	public List<Improvement> getImprovementList(Improvement vo) {
		// TODO Auto-generated method stub
		return repository.getImprovementList(vo);
	}
	
	@Override
	public List<Improvement> getLeaderImprovementList(Improvement vo) {
		// TODO Auto-generated method stub
		return repository.getLeaderImprovementList(vo);
	}
	
	@Override
	public Amount getAccidentsPrevention(Amount vo) {
		// TODO Auto-generated method stub
		return repository.getAccidentsPrevention(vo);
	}
	
	
	@Override
	public Amount getImprovemetLawOrder(Amount vo) {
		// TODO Auto-generated method stub
		return repository.getImprovemetLawOrder(vo);
	}		
	
	@Override
	public Baseline getDayInfo(Baseline vo) {
		// TODO Auto-generated method stub
		return repository.getDayInfo(vo);
	}


	@Override
	@Transactional
	public int insertEssentialDuty(List<LinkedHashMap<String, String>> vo, Login login) {
		// TODO Auto-generated method stub
		int result = 0;
		MainExcelData med = new MainExcelData();
		med.setCompanyId(login.getCompanyId());
		//int baseCnt = repository.getBaselineConfirm(med);
		//log.info("baseCnt : " + baseCnt);
		
		//if(baseCnt > 0) {
		for(int i=0; i < vo.size(); i++) {
			repository.insertEssentialDuty(vo.get(i));
		}
		result = 1;			
		//}
		
		log.info("result : " + result);
		return result;
	}

	
	@Override
	@Transactional
	public int insertEssentialDutyUser(MainExcelData vo) {
		// TODO Auto-generated method stub
		int result = 0;
		int baseCnt = repository.getBaselineConfirm(vo);
		
		if(baseCnt > 0) {
			int cnt = repository.getEssentialDutyUserCnt(vo);
			if (cnt <= 0) {
				MainExcelData version = repository.getEssentialDutyVersionDate();
				
				if(version!=null) {
					List<MainExcelData> resultList = repository.getEssentialDuty(version);
					if(resultList!=null) {
						for(int i=0; i < resultList.size(); i++) {
							resultList.get(i).setWorkplaceId(vo.getWorkplaceId());
							resultList.get(i).setBaselineId(vo.getBaselineId());
							resultList.get(i).setBaselineStart(vo.getBaselineStart());
							resultList.get(i).setBaselineEnd(vo.getBaselineEnd());
							repository.insertEssentialDutyUser(resultList.get(i));
						}
						
						if(resultList.size() > 0) {
							result = 1;
						}				
					}				
				}
			}			
		}else {
			result = 9001;
		}
		
		return result;		
	}


	@Override
	public void updateScore(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		repository.updateScore(vo);
	}


	@Override
	public void updateDocumentFileId(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		repository.updateDocumentFileId(vo);
	}	
	
	
	@Override
	public void updateRelatedArticle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		repository.updateRelatedArticle(vo);
	}	
	
	
	@Override
	public EssentialInfo getEssentialRate(PramAmount vo) {
		// TODO Auto-generated method stub
		
		List<Integer> rateList = new ArrayList<Integer>(); 
		int topRate = 0;
		
		EssentialRate r1 = this.getRate(rateList, vo, ExcelTitleType.TITLE1.getCode(), ExcelTitleType.TITLE1.getName());
		EssentialRate r2 = this.getRate(rateList, vo, ExcelTitleType.TITLE2.getCode(), ExcelTitleType.TITLE2.getName());
		EssentialRate r3 = this.getRate(rateList, vo, ExcelTitleType.TITLE3.getCode(), ExcelTitleType.TITLE3.getName());
		EssentialRate r4 = this.getRate(rateList, vo, ExcelTitleType.TITLE4.getCode(), ExcelTitleType.TITLE4.getName());
		EssentialRate r5 = this.getRate(rateList, vo, ExcelTitleType.TITLE5.getCode(), ExcelTitleType.TITLE5.getName());
		EssentialRate r6 = this.getRate(rateList, vo, ExcelTitleType.TITLE6.getCode(), ExcelTitleType.TITLE6.getName());
		EssentialRate r7 = this.getRate(rateList, vo, ExcelTitleType.TITLE7.getCode(), ExcelTitleType.TITLE7.getName());
		EssentialRate r8 = this.getRate(rateList, vo, ExcelTitleType.TITLE8.getCode(), ExcelTitleType.TITLE8.getName());
		EssentialRate r9 = this.getRate(rateList, vo, ExcelTitleType.TITLE9.getCode(), ExcelTitleType.TITLE9.getName());
		
		EssentialInfo eInfo = new EssentialInfo();
		eInfo.setRate1(r1);
		eInfo.setRate2(r2);
		eInfo.setRate3(r3);
		eInfo.setRate4(r4);
		eInfo.setRate5(r5);
		eInfo.setRate6(r6);
		eInfo.setRate7(r7);
		eInfo.setRate8(r8);
		eInfo.setRate9(r9);
		
		for(int i=0;i<rateList.size();i++) {
			topRate += rateList.get(i);
		}		
		
		topRate = (int) Math.floor(topRate/ExcelTitleType.values().length);
		
		String topScore = "";
		if(topRate >= 90) {
			//정상
			topScore = "normal";
		}else if(topRate <= 90 && topRate >= 80) {
			//주의
			topScore = "caution";
		}else if(topRate <= 79 && topRate >= 70) {
			//경고
			topScore = "warning";
		}else if(topRate < 70) {
			//위험
			topScore = "danger";
		}
		
		eInfo.setTopScore(topScore);
		eInfo.setTopRate(topRate+"%");
		return eInfo;
	}		

	
	public EssentialRate getRate(
			List<Integer> rateList
			, PramAmount vo
			, String code
			, String rateTitle) {
		
		int targetRate = 0;
		EssentialRate er = new EssentialRate();
		
		try {
			vo.setGroupId(code);
			Amount at = repository.getEssentialRate(vo);
			if(at!=null) {
				targetRate = at.getEvaluationRate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {			
			//result.put(rateTitle, targetRate+"%");
			
			if(vo.getWorkplaceId()==null) {
				targetRate = (int) Math.floor(targetRate/vo.getWorkplaceSize());
			}
			
			er.setGroupId(vo.getGroupId());
			er.setTitle(rateTitle);
			er.setScore(targetRate+"%");
			rateList.add(targetRate);
			targetRate = 0;
		}
		return er;
	}	
	
	
	@Override
	public List<MainExcelData> getDutyDetailList(ParamMainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getDutyDetailList(vo);
	}	
	
	@Override
	public List<MainExcelData> getInspectiondocs(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getInspectiondocs(vo);
	}
	
	@Override
	public List<MainExcelData> getDutyCyle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getDutyCyle(vo);
	}	
	
	@Override
	public List<MainExcelData> getDutyAssigned(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getDutyAssigned(vo);
	}	
	
	@Override
	public List<MainExcelData> getRelatedArticle(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getRelatedArticle(vo);
	}	
	
	@Override
	public List<MainExcelData> getGuideLine(ParamDutyCyle vo) {
		// TODO Auto-generated method stub
		return repository.getGuideLine(vo);
	}	
	
	@Override
	public AccidentsAmount getAccidentTotal(AccidentsAmount vo) {
		// TODO Auto-generated method stub
		return repository.getAccidentTotal(vo);
	}
	
	@Override
	public Amount getRelatedLawRate(PramAmount vo) {
		// TODO Auto-generated method stub
		return repository.getRelatedLawRate(vo);
	}			

	@Override
	public SafeWork getSafeWorkHistoryList(ParamSafeWork vo) {
		// TODO Auto-generated method stub
		return repository.getSafeWorkHistoryList(vo);
	}	
		
	@Override
	public Graph getBaseLineReportGraph(Report vo) {
		// TODO Auto-generated method stub
		
		Graph returnData = new Graph();
		List<Series> graph = new ArrayList<Series>();
		List<String> categories = new ArrayList<String>();
		//사업장리스트정보
		//List<Report> workplace = repository.getTitleReport1(vo);
		
		//사업장정보목록
		Workplace params = new Workplace(); 
		params.setCompanyId(vo.getCompanyId());
		params.setWorkplaceId(vo.getWorkplaceId());
		params.setRoleCd(vo.getRoleCd());
		List<Workplace> workplace = this.getWorkplaceList(params);		
		
		if(workplace!=null) {
			Series g = new Series();
			List<Report> reportTitle = repository.getTitleReport2(vo);
			for(Workplace w : workplace) {		
				vo.setWorkplaceId(w.getWorkplaceId());
				List<Integer> data = new ArrayList<Integer>();			
				List<Report> report1 = repository.getBaseLineReport1(vo);
				g = new Series();
				categories = new ArrayList<String>();
				if(report1!=null && report1.size()>0) {
					for(Report r : report1) {
						if(r!=null) {
							categories.add(r.getMenuTitle());
							data.add(r.getEvaluationRate());						
						}else {
							categories.add(reportTitle.get(0).getMenuTitle());
							data.add(0);							
						}	
					}
				}else {
					categories.add(reportTitle.get(0).getMenuTitle());
					data.add(0);
				}
				List<Report> report2 = repository.getBaseLineReport2(vo);
				g = new Series();
				if(report2!=null && report2.size()>0) {
					for(Report r : report2) {
						if(r!=null) {
							categories.add(r.getMenuTitle());
							data.add(r.getEvaluationRate());						
						}else {
							categories.add(reportTitle.get(1).getMenuTitle());
							data.add(0);							
						}	
					}
				}else {
					categories.add(reportTitle.get(1).getMenuTitle());
					data.add(0);
				}	
				List<Report> report3 = repository.getBaseLineReport3(vo);
				g = new Series();
				if(report3!=null && report3.size()>0) {			
					for(Report r : report3) {
						if(r!=null) {
							categories.add(r.getMenuTitle());
							data.add(r.getEvaluationRate());						
						}else {
							categories.add(reportTitle.get(2).getMenuTitle());
							data.add(0);							
						}							
					}
				}else {
					categories.add(reportTitle.get(2).getMenuTitle());
					data.add(0);
				}
				List<Report> report4 = repository.getBaseLineReport4(vo);
				g = new Series();
				if(report4!=null && report4.size()>0) {		
					for(Report r : report4) {
						if(r!=null) {
							categories.add(r.getMenuTitle());
							data.add(r.getEvaluationRate());						
						}else {
							categories.add(reportTitle.get(3).getMenuTitle());
							data.add(0);							
						}						
					}
				}else {
					categories.add(reportTitle.get(3).getMenuTitle());
					data.add(0);
				}
				
				g.setName(w.getWorkplaceName());
				g.setData(data);
				graph.add(g);					
			}
			
			returnData.setSeries(graph);
			returnData.setCategories(categories);			
		}
	
		return returnData;
		
	}
	
	@Override
	public Graph getWorkPlaceReportGraph(Report vo) {
		// TODO Auto-generated method stub
		
		Graph returnData = new Graph();
		List<Series> graph = new ArrayList<Series>();
		List<String> categories = new ArrayList<String>();
		//사업장리스트정보
		//List<Report> workplace = repository.getTitleReport1(vo);
		
		//사업장정보목록
		Workplace params = new Workplace(); 
		params.setCompanyId(vo.getCompanyId());
		params.setWorkplaceId(vo.getWorkplaceId());
		params.setRoleCd(vo.getRoleCd());
		List<Workplace> workplace = this.getWorkplaceList(params);			
		
		if(workplace!=null) {
			List<Report> reportTitle = repository.getTitleReport2(vo);
			Series g = new Series();
			
			categories = new ArrayList<String>();
		
			
			for(Report rt : reportTitle) {
				g = new Series();
				if("0".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getWorkPlaceReport(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);						
				}else if("10".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> report2 = repository.getBaseLineReport2(vo);
						g = new Series();
						if(report2!=null && report2.size()>0) {
							for(Report r : report2) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
					}
					g.setData(data);
					graph.add(g);	
				}else if("11".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> report3 = repository.getBaseLineReport3(vo);
						g = new Series();
						if(report3!=null && report3.size()>0) {
							for(Report r : report3) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
					}					
					g.setData(data);
					graph.add(g);	
				}else if("12".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> report4 = repository.getBaseLineReport4(vo);
						g = new Series();
						if(report4!=null && report4.size()>0) {
							for(Report r : report4) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}		
						g.setName(rt.getMenuTitle());
					}					
					g.setData(data);
					graph.add(g);	
				}
			}
		}				
		returnData.setSeries(graph);
		returnData.setCategories(categories);				
		return returnData;
	}	
	
	@Override
	public Graph getItemsReportGraph(Report vo) {
		// TODO Auto-generated method stub
		Graph returnData = new Graph();
		List<Series> graph = new ArrayList<Series>();
		//사업장리스트정보
		//List<Report> workplace = repository.getTitleReport1(vo);
		
		//사업장정보목록
		Workplace params = new Workplace(); 
		params.setCompanyId(vo.getCompanyId());
		params.setWorkplaceId(vo.getWorkplaceId());
		params.setRoleCd(vo.getRoleCd());
		List<Workplace> workplace = this.getWorkplaceList(params);			
		List<Report> title = repository.getTitleReport4(vo);
		
		if(workplace!=null) {
			List<Report> reportTitle = repository.getTitleReport4(vo);
			Series g = new Series();
			int i = 0;
			for(Workplace w : workplace) {
				vo.setWorkplaceId(w.getWorkplaceId());
				List<Integer> data = new ArrayList<Integer>();			
				List<Report> workPalceReport = repository.getItemsReportGraph(vo);
				g = new Series();
				if(workPalceReport!=null && workPalceReport.size()>0) {
					for(Report r : workPalceReport) {
						if(r!=null) {
							data.add(r.getEvaluationRate());							
						}else {
							data.add(0);					
						}						
					}
				}else {
					g.setName(reportTitle.get(i).getMenuTitle());					
					for(int j=0;j < 9;j++) {
						data.add(0);
					}
				}
				
				List<Report> report2 = repository.getBaseLineReport2(vo);
				g = new Series();
				if(report2!=null && report2.size()>0) {
					for(Report r : report2) {
						if(r!=null) {
							data.add(r.getEvaluationRate());
						}else {
							data.add(0);					
						}							
					}
				}else {
					data.add(0);
				}	
				List<Report> report3 = repository.getBaseLineReport3(vo);
				g = new Series();
				if(report3!=null && report3.size()>0) {			
					for(Report r : report3) {
						if(r!=null) {
							data.add(r.getEvaluationRate());
						}else {
							data.add(0);					
						}
					}
				}else {
					data.add(0);
				}
				List<Report> report4 = repository.getBaseLineReport4(vo);
				g = new Series();
				if(report4!=null && report4.size()>0) {		
					for(Report r : report4) {
						if(r!=null) {
							data.add(r.getEvaluationRate());
						}else {
							data.add(0);					
						}						
					}
				}else {
					data.add(0);
				}				
								
				g.setName(w.getWorkplaceName());
				g.setData(data);
				graph.add(g);
				i++;
			}
			
			List<String> categories = new ArrayList<String>();
			for (Report t : title) {
				categories.add(t.getMenuTitle());
			}				
			
			returnData.setSeries(graph);
			returnData.setCategories(categories);			
		}
	
		return returnData;
	}		

	
	@Override
	public Graph getItemsWorkPlaceReportGraph(Report vo) {

		// TODO Auto-generated method stub
		
		Graph returnData = new Graph();
		List<Series> graph = new ArrayList<Series>();
		List<String> categories = new ArrayList<String>();
		//사업장리스트정보
		//List<Report> workplace = repository.getTitleReport1(vo);
		
		//사업장정보목록
		Workplace params = new Workplace(); 
		params.setCompanyId(vo.getCompanyId());
		params.setWorkplaceId(vo.getWorkplaceId());
		params.setRoleCd(vo.getRoleCd());
		List<Workplace> workplace = this.getWorkplaceList(params);			
		
		if(workplace!=null) {
			List<Report> reportTitle = repository.getTitleReport4(vo);
			Series g = new Series();
			
			categories = new ArrayList<String>();
		
			
			for(Report rt : reportTitle) {
				g = new Series();
				if("1".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);						
				}else if("2".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);		
				}else if("3".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);		
				}else if("4".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);	
				}else if("5".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);		
				}else if("6".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);	
				}else if("7".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);	
				}else if("8".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);	
				}else if("9".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setGroupId(rt.getGroupId());
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> workPalceReport = repository.getItemsWorkPlaceReportGraph(vo);
						g = new Series();
						if(workPalceReport!=null && workPalceReport.size()>0) {
							for(Report r : workPalceReport) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
						categories.add(w.getWorkplaceName());							
					}
					g.setData(data);
					graph.add(g);	
				}else if("10".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> report2 = repository.getBaseLineReport2(vo);
						g = new Series();
						if(report2!=null && report2.size()>0) {
							for(Report r : report2) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
					}
					g.setData(data);
					graph.add(g);	
				}else if("11".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> report3 = repository.getBaseLineReport3(vo);
						g = new Series();
						if(report3!=null && report3.size()>0) {
							for(Report r : report3) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}			
						g.setName(rt.getMenuTitle());
					}					
					g.setData(data);
					graph.add(g);	
				}else if("12".equals(rt.getGroupId())) {
					List<Integer> data = new ArrayList<Integer>();
					for(Workplace w : workplace) {
						vo.setWorkplaceId(w.getWorkplaceId());
						
						List<Report> report4 = repository.getBaseLineReport4(vo);
						g = new Series();
						if(report4!=null && report4.size()>0) {
							for(Report r : report4) {
								if(r!=null) {
									data.add(r.getEvaluationRate());							
								}else {
									data.add(0);							
								}
							}
						}else {
							data.add(0);
						}		
						g.setName(rt.getMenuTitle());
					}					
					g.setData(data);
					graph.add(g);	
				}
				
			}
		}				
		returnData.setSeries(graph);
		returnData.setCategories(categories);				
		return returnData;
			
	}			

	@Override
	public Graph getAccidentsPreventionReportGraph(Report vo) {
		// TODO Auto-generated method stub
		Graph returnData = new Graph();
		List<List<Report>> result = new ArrayList<List<Report>>();
		List<Series> graph = new ArrayList<Series>();

		//사업장정보목록
		Workplace params = new Workplace(); 
		params.setCompanyId(vo.getCompanyId());
		params.setWorkplaceId(vo.getWorkplaceId());
		params.setRoleCd(vo.getRoleCd());
		List<Workplace> workplace = this.getWorkplaceList(params);			
		
		if(workplace!=null) {
			
			for(Workplace w : workplace) {
				vo.setWorkplaceId(w.getWorkplaceId());
				List<Report> workPalceReport = repository.getAccidentsPreventionReport(vo);
				if(workPalceReport!=null && workPalceReport.size()>0) {
					result.add(workPalceReport);
				}
			}
			
			for(List<Report> r : result) {
				String title = "";
				List<Integer> data = new ArrayList<Integer>();
				Series g = new Series();
				for(int i = 0; i<r.size() ;i++) {
					if(i==0) {
						title = r.get(i).getWorkplaceName();
					}				
					
					data.add(Integer.parseInt(r.get(i).getAccType001()));
					data.add(Integer.parseInt(r.get(i).getAccType002()));
					data.add(Integer.parseInt(r.get(i).getAccType003()));
					data.add(Integer.parseInt(r.get(i).getAccType004()));
					data.add(Integer.parseInt(r.get(i).getAccType005()));
					data.add(Integer.parseInt(r.get(i).getAccType006()));
				}
				g.setName(title);
				g.setData(data);
				graph.add(g);
			}		
			
			returnData.setSeries(graph);
			List<String> categories = new ArrayList<String>();
			List<Report> title = repository.getTitleReport5(vo);
			for (Report t : title) {
				categories.add(t.getMenuTitle());
			}
			
			returnData.setCategories(categories);			
			
		}
		return returnData;	
	}


	@Override
	public Graph getImprovemetLawOrderReportGraph(Report vo) {
		// TODO Auto-generated method stub
		Graph returnData = new Graph();
		List<List<Report>> result = new ArrayList<List<Report>>();
		List<Series> graph = new ArrayList<Series>();
		
		//사업장정보목록
		Workplace params = new Workplace(); 
		params.setCompanyId(vo.getCompanyId());
		params.setWorkplaceId(vo.getWorkplaceId());
		params.setRoleCd(vo.getRoleCd());
		List<Workplace> workplace = this.getWorkplaceList(params);	
		
		if(workplace!=null) {
			
			for(Workplace w : workplace) {
				vo.setWorkplaceId(w.getWorkplaceId());
				List<Report> workPalceReport = repository.getImprovemetLawOrderReport(vo);
				if(workPalceReport!=null && workPalceReport.size()>0) {
					result.add(workPalceReport);
				}
			}
			
			
			for(List<Report> r : result) {
				String title = "";
				List<Integer> data = new ArrayList<Integer>();
				Series g = new Series();
				for(int i = 0; i<r.size() ;i++) {
					if(i==0) {
						title = r.get(i).getWorkplaceName();
					}				
					
					//categories.add(r.get(i).getMenuTitle());
					data.add(Integer.parseInt(r.get(i).getCmmdOrgCd001()));
					data.add(Integer.parseInt(r.get(i).getCmmdOrgCd002()));
					data.add(Integer.parseInt(r.get(i).getCmmdOrgCd003()));
					data.add(Integer.parseInt(r.get(i).getCmmdOrgCd004()));
				}
				g.setName(title);
				g.setData(data);
				graph.add(g);
			}		
			
			returnData.setSeries(graph);
			
			List<String> categories = new ArrayList<String>();
			List<Report> title = repository.getTitleReport6(vo);
			for (Report t : title) {
				categories.add(t.getMenuTitle());
			}
			
			returnData.setCategories(categories);				
		}
		return returnData;	
	}
		
	

	@Override
	public List<Report> getTitleReport(Report vo) {
		// TODO Auto-generated method stub
		 List<Report> result = new  ArrayList<Report>();
		if("1".equals(vo.getCondition())) {//차수별 대응수전 현황(통합)
			return repository.getTitleReport1(vo);
		}else if("2".equals(vo.getCondition())) {//차수별 대응수전 현황(사업장별)
			return repository.getTitleReport2(vo);
		}else if("3".equals(vo.getCondition())) {//항목별대응수준 현황(통합)
			return repository.getTitleReport1(vo);
		}else if("4".equals(vo.getCondition())) {//항목별대응수준 현황(사업장별)
			return repository.getTitleReport4(vo);
		}else if("5".equals(vo.getCondition())) {
			return repository.getTitleReport5(vo);
		}else if("6".equals(vo.getCondition())) {
			return repository.getTitleReport6(vo);
		}
		return result;
	}


	@Override
	public List<List<Report>> getBaseLineReport(Report vo) {
		// TODO Auto-generated method stub
		List<List<Report>> result = new ArrayList<List<Report>>();
		List<Report> report1 = repository.getBaseLineReport1(vo);
		if(report1!=null && report1.size()>0) {
			result.add(report1);
		}
		List<Report> report2 = repository.getBaseLineReport2(vo);
		if(report2!=null && report2.size()>0) {
			result.add(report2);
		}		
		List<Report> report3 = repository.getBaseLineReport3(vo);
		if(report3!=null && report3.size()>0) {			
			result.add(report3);
		}		
		List<Report> report4 = repository.getBaseLineReport4(vo);
		if(report4!=null && report4.size()>0) {		
			result.add(report4);
		}		
		return result;
		
	}
	
	@Override
	public List<List<Report>> getWorkPlaceReport(Report vo) {
		// TODO Auto-generated method stub
		List<List<Report>> result = new ArrayList<List<Report>>();
		
		//사업장리스트정보
		List<Report> workplace = repository.getTitleReport1(vo);
		if(workplace!=null) {
			
			for(Report w : workplace) {
				vo.setWorkplaceId(Long.parseLong(w.getGroupId()));
				List<Report> workPalceReport = repository.getWorkPlaceReport(vo);
				if(workPalceReport!=null && workPalceReport.size()>0) {
					result.add(workPalceReport);
				}
			}
		}
		
		return result;
	}	
	
	@Override
	public List<List<Report>> getItemsReport(Report vo) {
		// TODO Auto-generated method stub
		List<List<Report>> result = new ArrayList<List<Report>>();
		
		//사업장리스트정보
		List<Report> workplace = repository.getTitleReport1(vo);
		if(workplace!=null) {
			
			for(Report w : workplace) {
				vo.setWorkplaceId(Long.parseLong(w.getGroupId()));
				List<Report> workPalceReport = repository.getItemsReport(vo);
				if(workPalceReport!=null && workPalceReport.size()>0) {
					result.add(workPalceReport);
				}
			}
		}
		
		return result;
	}		


	@Override
	public List<List<Report>> getAccidentsPreventionReport(Report vo) {
		// TODO Auto-generated method stub
		List<List<Report>> result = new ArrayList<List<Report>>();
		
		//사업장리스트정보
		Report params = new Report();
		params.setCompanyId(vo.getCompanyId());
		List<Report> workplace = repository.getTitleReport1(params);
		if(workplace!=null) {
			
			for(Report w : workplace) {
				vo.setWorkplaceId(Long.parseLong(w.getGroupId()));
				List<Report> workPalceReport = repository.getAccidentsPreventionReport(vo);
				if(workPalceReport!=null && workPalceReport.size()>0) {
					result.add(workPalceReport);
				}
			}
		}
		
		return result;		
	}


	@Override
	public List<List<Report>> getImprovemetLawOrderReport(Report vo) {
		// TODO Auto-generated method stub
		List<List<Report>> result = new ArrayList<List<Report>>();
		
		//사업장리스트정보
		Report params = new Report();
		params.setCompanyId(vo.getCompanyId());
		List<Report> workplace = repository.getTitleReport1(params);
		if(workplace!=null) {
			
			for(Report w : workplace) {
				vo.setWorkplaceId(Long.parseLong(w.getGroupId()));
				List<Report> workPalceReport = repository.getImprovemetLawOrderReport(vo);
				if(workPalceReport!=null && workPalceReport.size()>0) {
					result.add(workPalceReport);
				}
			}
		}
		
		return result;		
	}

	@Override
	public int getEssentialDutyMasterCnt(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getEssentialDutyMasterCnt(vo);
	}


	@Override
	public int getEssentialDutyUserCnt(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getEssentialDutyUserCnt(vo);
	}


	@Override
	public int getBaselineConfirm(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getBaselineConfirm(vo);
	}	
	

	@Override
	@Transactional
	public int insertSafeWorkExcelUpload(List<LinkedHashMap<String, String>> vo, ParamSafeWork login) {
		// TODO Auto-generated method stub
		int result = 0;
		MainExcelData med = new MainExcelData();
		med.setCompanyId(login.getCompanyId());
		log.info("insertSafeWorkExcelUpload getBaselineConfirm param : {}",  med);
		int baseCnt = repository.getBaselineConfirm(med);
		
		if(baseCnt > 0) {
			for(int i=0; i < vo.size(); i++) {
				repository.insertSafeWorkExcelUpload(vo.get(i));
			}
			result = 1;			
		}else {
			result = 9001;
		}
		return result;		
	}


	@Override
	public int getSafetyFileCnt(Setting vo) {
		// TODO Auto-generated method stub
		return repository.getSafetyFileCnt(vo);
	}	
	
	@Override
	public void updateUserCompanyLogoId(Setting vo) {
		// TODO Auto-generated method stub
		repository.updateUserCompanyLogoId(vo);
	}		
	
	
	@Override
	public void updateUserCompany(Setting vo) {
		// TODO Auto-generated method stub
		repository.updateUserCompany(vo);
	}	
		
	
	@Override
	public int insertBaseline(Setting vo) {
		// TODO Auto-generated method stub
		int cnt = repository.getBaselineCnt(vo);
		
		Setting bcheck = repository.getCheckBaseline(vo);
		
		if(!bcheck.getBaselineCheck()) {
			return 999;
	    }else if(cnt > 0) {
			return 998;//중복된 차수가 존재
		}else {
			return repository.insertBaseline(vo);
		}
	}		
	
	
	@Override
	public void updateSafetyFile(Setting vo) {
		// TODO Auto-generated method stub
		repository.updateSafetyFile(vo);
	}		
	
		
	@Override
	@Transactional
	public BaseResponse<Integer> insertBaseLineDataCopy(MainExcelData vo) throws Exception{
		// TODO Auto-generated method stub
		// 1 : 성공 , 2 : 동일한 데이터 발생, 0 : 저장할데이터없음
		int result = 0;
		
		//복사할 차수가 있는지 확인
		//int baseCnt = repository.getBaselineCopyConfirm(vo);
		//if(baseCnt > 0) { // 복사할 정보가 있다면 
			
			
			Baseline b = new Baseline();
			b.setCompanyId(vo.getCompanyId());
			b.setBaselineId(vo.getBaselineId());
			Baseline baseLineInfo = repository.getBaseline(b);		
			
			if(baseLineInfo!=null) {
				
				//동일한 데이터 확인
				int edcfCnt = repository.getEducdDataConfirm(vo);
				if(edcfCnt > 0) {
					//return new BaseResponse<Integer>(BaseResponseCode.SAME_ERROR);
					//삭제
					repository.deleteEducdData(vo);
				}				
				
				//복사할필수의무조치내역확인
				List<MainExcelData> resultList = repository.getEssentialDutyUserCopyData(vo);
				if(resultList!=null) {
					for(int i=0; i < resultList.size(); i++) {
						resultList.get(i).setWorkplaceId(vo.getWorkplaceId());
						resultList.get(i).setBaselineId(vo.getTargetBaselineId());
						resultList.get(i).setBaselineStart(baseLineInfo.getBaselineStart());
						resultList.get(i).setBaselineEnd(baseLineInfo.getBaselineEnd());
						repository.insertBaseLineDataCopy(resultList.get(i));
					}
					
					if(resultList.size() > 0) {
						result = 1;
					}else {
						result = 0;
					}
					
				}	
				
				
				//복사할 관계법령에 의무이행의 관리의 조치
				RelatedLaw rl = new RelatedLaw();
				rl.setCompanyId(vo.getCompanyId());
				rl.setWorkplaceId(vo.getWorkplaceId());
				rl.setBaselineId(vo.getBaselineId());
				rl.setTargetBaselineId(vo.getTargetBaselineId());
				
				//동일한 데이터 확인
				int rrCnt = rlRepository.getRrcdDataConfirm(rl);
				if(rrCnt > 0) {
					//return new BaseResponse<Integer>(BaseResponseCode.SAME_ERROR);
					//삭제
					rlRepository.deleteRrcdData(rl);
				}				
				
				List<RelatedLaw> resultList2 = rlRepository.getRelatedRawCopyData(rl);
				if(resultList2!=null) {
					for(int i=0; i < resultList2.size(); i++) {
						resultList2.get(i).setBaselineId(vo.getTargetBaselineId());
						resultList2.get(i).setCompanyId(vo.getCompanyId());
						resultList2.get(i).setWorkplaceId(vo.getWorkplaceId());
						resultList2.get(i).setInsertId(vo.getInsertId());
						rlRepository.insertRelatedRawCopy(resultList2.get(i));
					}
					
					
					if(resultList2.size() > 0) {
						result = 1;
					}else {
						result = 0;
					}				
					
				}		
			}
			
		if(result==1) {
			return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);		
		}else {
			return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL);		
		}	
	}	
	
	@Override
	public void closeBaseline(Long companyId, Long baselineId, Long updateId) {
		repository.closeBaseline(companyId, baselineId, updateId);
	}	
	
	
	@Override
	@Transactional
	public int insertBaseLineDataUpdate(MainExcelData vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
			
		//Baseline b = new Baseline();
		//b.setCompanyId(vo.getCompanyId());
		//b.setBaselineId(vo.getBaselineId());
		//Baseline baseLineInfo = repository.getBaseline(b);		
		
		
		//if(baseLineInfo!=null) {
			//동일한 데이터 삭제
			repository.deleteEssentialDutyUser(vo);			
			MainExcelData version = repository.getEssentialDutyVersionDate();
			if(version!=null) {
				List<MainExcelData> resultList = repository.getEssentialDuty(version);
				if(resultList!=null) {
					for(int i=0; i < resultList.size(); i++) {
						resultList.get(i).setWorkplaceId(vo.getWorkplaceId());
						resultList.get(i).setBaselineId(vo.getBaselineId());
						resultList.get(i).setBaselineStart(vo.getBaselineStart());
						resultList.get(i).setBaselineEnd(vo.getBaselineEnd());
						repository.insertEssentialDutyUser(resultList.get(i));
					}
					
					if(resultList.size() > 0) {
						result = 1;
					}				
				}				
			}
		//}
		return result;		
	}


	@Override
	public int getBaseLineDataCnt(MainExcelData vo) {
		// TODO Auto-generated method stub
		return repository.getBaseLineDataCnt(vo);
	}


	@Override
	public JSONObject HttpURLConnection(String strURL, String strParams) {
		// TODO Auto-generated method stub
		JSONObject jsonData = new JSONObject();
        try {
            URL url = new URL(strURL + "?" + strParams); //get 방식은 parameter를 URL에 묶어서 보낸다.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("GET");
 
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "ko-kr");
            conn.setRequestProperty("Access-Control-Allow-Origin", "*");
            conn.setRequestProperty("Content-Type", "application/json");
 
            conn.setConnectTimeout(10000); // 커넥션 timeout 10초
            conn.setReadTimeout(5000); //컨텐츠 조회시 timeout 5초
 
            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            jsonData = new JSONObject(response.toString());
            
        } catch (MalformedURLException e) {
            //URL
            e.printStackTrace();
        } catch (IOException e) {
            //HttpURLConnection
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return jsonData;
        
	}

	
	@Override
	public String getWeatherAddress(JSONObject jsonData) {
		// TODO Auto-generated method stub
		
		String address = "구로디지털단지"; 
		if(jsonData!=null) {
	        try {
	            Map<String, Object> map =  new ObjectMapper().readValue(jsonData.toString(), Map.class);
	            Map<String, Object> response = (Map<String, Object>) map.get("response");
	            List<Map<String, Object>> result = (List<Map<String, Object>>) response.get("result");
	            if(result!=null) {
	            	address = (String) result.get(0).get("text");
	            }	            
	        } catch (MalformedURLException e) {
	            //URL
	            e.printStackTrace();
	        } catch (IOException e) {
	            //HttpURLConnection
	            e.printStackTrace();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }					
		}
		return address;
	}
		
	

	@Override
	public Weather getWeather(JSONObject jsonData, String addr) {
    	Weather w = new Weather(); 
    	w.setLatitude(37.487216103788334);
    	w.setLongitude(126.89456191647437);
		w.setTemperature((double) 26);
		w.setAddress(addr);
		//w.setWeatherImgUrl("/home/jun/apps/riskfree/webapps/static_file/fine.png");         	
		w.setWeatherImgUrl(GlobalsProperties.getProperty("Globals.imgStorePath")+"/fine.png");         	
    	
		if(jsonData!=null) {
	        try {
	            Map<String, Object> map =  new ObjectMapper().readValue(jsonData.toString(), Map.class);
	            Map<String, Object> coord = (Map<String, Object>) map.get("coord");
	            if(coord!=null) {
	            	w.setLatitude((Double)coord.get("lon"));
	                w.setLongitude((Double)coord.get("lat"));	
	            }
	            
	            List<Map<String, Object>> weather = (List<Map<String, Object>>) map.get("weather");
	            if(weather!=null) {
	            	System.out.println(weather.get(0).get("id"));
	            	/*
	            	 * 맑음 fine.png -> 800
						구름 cloudy.png -> 80x
						구름낀맑음 partially_cloudy.png-> 7xx
						비 rainy.png-> 5xx, 3xx, 2xx
						눈 snowy.png-> 6xx
	            	 * */
	            	String targetStr = weather.get(0).get("id").toString().substring(0, 1);
	            	System.out.println("substring : "+targetStr);
	            	
	            	if(weather.get(0).get("id").equals("800")) {//맑음
	            		w.setWeatherImgUrl(WeatherInfo.FINE.getValue());
	            	}else if(targetStr.equals("7")){//구름낀 맑음
	            		w.setWeatherImgUrl(WeatherInfo.PARTIALLY_CLOUDY.getValue());
	            	}else if(targetStr.equals("5") || targetStr.equals("3") || targetStr.equals("2")){//비
	            		w.setWeatherImgUrl(WeatherInfo.RAINY.getValue());
	            	}else if(targetStr.equals("6")){//눈
	            		w.setWeatherImgUrl(WeatherInfo.SNOWY.getValue());
	            	}else {//구름
	            		w.setWeatherImgUrl(WeatherInfo.CLOUDY.getValue());
	            	}
	            }
	            
	            Map<String, Object> main = (Map<String, Object>) map.get("main");
	            System.out.println();
	            if(weather!=null) {
	            	w.setTemperature((Double) main.get("temp"));
	            }
	            w.setAddress(addr);
	        } catch (MalformedURLException e) {
	            //URL
	            e.printStackTrace();
	        } catch (IOException e) {
	            //HttpURLConnection
	            e.printStackTrace();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }			
		}
		
        return w;
	}

	@Override
	public int getNowNoticeList(Notice vo) {
		// TODO Auto-generated method stub
		return repository.getNowNoticeList(vo);
	}	
	
	@Override
	public MainExcelData getEssentialDutyVersion() {
		// TODO Auto-generated method stub
		return repository.getEssentialDutyVersion();
	}

}
