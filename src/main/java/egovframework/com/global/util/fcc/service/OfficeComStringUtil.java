package egovframework.com.global.util.fcc.service;

public  class OfficeComStringUtil {
	
	
	//public static final String RDB_MOBILE_GATEWAY_BASE_URL = GlobalsProperties.getProperty(Globals.MG_BASE_URL);
	public static  String RDB_MOBILE_GATEWAY_BASE_URL ="" ;
	
	// zimbra Login
	//public static  String URL_LOGIN				=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_login.do";
	public static  String URL_LOGIN				=	"rdbm_login.do";
	
	// View type
	public static final String VIEWTP_MESSAGE			= 	"message";
	public static final String VIEWTP_CONTACT			= 	"contact";
	public static final String VIEWTP_TASK				= 	"task";
	public static final String VIEWTP_CALENDAR			= 	"appointment";
	
	// folder
	/*public static  String URL_FOLDER_SELECTLIST	=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_get_folders.do";
	public static  String URL_FOLDER_INSERT		=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_create_folder.do";
	public static  String URL_FOLDER_RENAME		=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_rename_folder.do";
	public static  String URL_FOLDER_DELETE		=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_remove_folder.do";
	public static  String URL_FOLDER_SHARE			=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_request_share_folder.do";
	public static  String URL_FOLDER_EMPTY			=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_empty_folder.do";*/
	public static  String URL_FOLDER_SELECTLIST	=	"rdbm_get_folders.do";
	public static  String URL_FOLDER_INSERT		    =	"rdbm_create_folder.do";
	public static  String URL_FOLDER_RENAME		=	"rdbm_rename_folder.do";
	public static  String URL_FOLDER_DELETE		    =	"rdbm_remove_folder.do";
	public static  String URL_FOLDER_SHARE			=	"rdbm_request_share_folder.do";
	public static  String URL_FOLDER_EMPTY			=	"rdbm_empty_folder.do";
	
	
	
	// 스케쥴의 칼렌다 목록조회, 칼렌다 조회, 칼렌다 등록, 칼렌다 수정, 칼렌다 삭제
	public static final String URL_CAL_SELECTLIST 		= 	"";		
	public static final String URL_CAL_SELECTDTL		=	"";
	public static final String URL_CAL_INSERT		 	= 	"";
	public static final String URL_CAL_UPDATE			=	"";
	public static final String URL_CAL_DELETE			=	"";
	
	// 칼랜다의  스케쥴 목록조회, 스케쥴 조회, 스케쥴 등록, 스케쥴 수정, 스케쥴 삭제 
/*	public static  String URL_SCHED_LIST			=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_get_appt_summary.do";
	public static  String URL_SCHED_SELECTLIST		=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_get_appt_summary_by_folders.do";
	public static  String URL_SCHED_SELECTDTL		=	"";
	public static  String URL_SCHED_INSERT			=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_add_update_appointment.do";
	public static  String URL_SCHED_UPDATE			=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_add_update_appointment.do";
	public static  String URL_SCHED_DELETE			=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_move_appointments.do";
	public static  String URL_SCHED_MOVE			=	RDB_MOBILE_GATEWAY_BASE_URL + "rdbm_move_appointments.do";*/
    public static  String URL_SCHED_LIST				=	  "rdbm_get_appt_summary.do";
	public static  String URL_SCHED_SELECTLIST 	=	  "rdbm_get_appt_summary_by_folders.do";
	public static  String URL_SCHED_SELECTDTL		=		"";
	public static  String URL_SCHED_INSERT			=	  "rdbm_add_update_appointment.do";
	public static  String URL_SCHED_UPDATE			=	  "rdbm_add_update_appointment.do";
	public static  String URL_SCHED_DELETE			=	  "rdbm_move_appointments.do";
	public static  String URL_SCHED_MOVE 			=	  "rdbm_move_appointments.do";
	public static  String URL_SCHED_ACCEPT			=	  "rdbm_accept_appointment.do";
	public static  String URL_SCHED_DECLINE			=	  "rdbm_decline_appointment.do";
	public static  String URL_SCHED_RESPONSE			=	  "rdbm_response_appointment.do";
	
	public static  String URL_OOF_OWA_SET					=	  "rdbm_oofSettings.do";
	public static  String URL_OOF_OWA_GET				=	  "rdbm_oofGettings.do";

	
	// The result code of request to zimbra
	public static final String ZIMBRA_RSULT_SUCCESS		= "0000";
	public static final String ZIMBRA_RSULT_FAIL		= "";
	
	public static String MS_DOMAIN_NAME ="" ;
	public static String NOTI_SENDER_ID = "";
	public static String NOTI_SENDER_PW = "";
	public static String ADMIN_EMAIL_ADDRESS = "";

}
