//$Id: SearchTestUtil.java,v 1.1 2003/11/10 10:42:09 sasikumar Exp $

package com.adventnet.nms.webclient.fault.alarm;

import com.adventnet.nms.webclient.components.advancedsearch.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.*;
import org.apache.struts.Globals;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.fe.common.CustomViewException;
import com.adventnet.nms.fe.common.TableColumn;
import com.adventnet.nms.webclient.common.WebClientUtil;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.NmsUtil;


public class TestSearch extends AdvancedSearchAction
{
    public ActionForward doProcess(HttpServletRequest request,ActionMapping mapping,Properties searchCriteria,boolean orOperation,String viewid)
    {
        Vector viewData = null;
        TableColumn[] headerList = null;
        String viewId = viewid;

        String displayName = "";
        long total = 0;

        String userName = (String)request.getSession().getAttribute(WebClientUtil.USER_KEY);

        //When we click the Alarm node from the tree, the viewId 
        //and displayName is passed as parameter.
        String tempviewId = request.getParameter("viewId");
        String tempdisplayName = request.getParameter("displayName");

        // After creating/modifying the custom view, we have to
        // show that view. So we will set the viewId and displayName
        // as attribute from the Action class.
        String tempviewIdAtt = (String)request.getAttribute("viewId");
        String tempdisplayAtt = (String)request.getAttribute("displayName");

        String viewLength = request.getParameter("viewLength");
        String startIndex = request.getParameter("FROM_INDEX");
        String orderByColumn = request.getParameter("orderByColumn");
        String isAscending = request.getParameter("isAscending");
        String entity = request.getParameter("entity");
        String pageNumber = request.getParameter("PAGE_NUMBER");

        Vector list = WebClientUtil.getModuleIncrements(userName, "Alerts");

        if (tempviewId != null)
        {
            viewId = tempviewId;
        }
        if (tempviewIdAtt != null)
        {
            viewId = tempviewIdAtt;
        }
        if (tempdisplayName != null)
        {
            displayName = tempdisplayName;
        }
        if (tempdisplayAtt != null)
        {
            displayName = tempdisplayAtt;
        }

        if (viewLength == null)
        {
            viewLength = (String)list.elementAt(0);
        }
        
        if (startIndex == null)
        {
            startIndex = "0";
        }

        if (isAscending == null)
        {
            isAscending = "false";
        }

        try
        {
            if ( entity != null)
            {
                Properties criteria = new Properties();
                criteria.setProperty("entity",entity);
                viewData = AlarmViewUtil.getInstance().fetchAlertViewData(viewId,viewLength,startIndex,orderByColumn,isAscending,userName,criteria);
            }
            else
            {
                viewData = AlarmViewUtil.getInstance().fetchAlertViewData(viewId,viewLength,startIndex,orderByColumn,isAscending,userName,searchCriteria);
            }
            ActionMessages actionMsg = new ActionMessages();
            if ( viewData.isEmpty() )
            {
                ActionMessage messageKey = new ActionMessage("webclient.fault.alarmlist.nodata");
                actionMsg.add("message",messageKey);
                request.setAttribute(Globals.MESSAGE_KEY, actionMsg);
                return mapping.findForward("messagePage");
            }

            headerList = AlarmViewUtil.getInstance().getHeaderList(viewId,userName);
            total = AlarmViewUtil.getInstance().getTotalLength();
            orderByColumn = AlarmViewUtil.getInstance().getOrderByColumn();
        }
        catch(Exception exe)
        {
            NmsLogMgr.ALERTERR.fail(NmsUtil.GetString("webclient.fault.alarmlist.exception.fetchingviewdata"),exe);
        }

        boolean permittedToClearAlert = GenericUtility.isAuthorized(userName, "Clear Alerts");
        boolean permittedToDeleteAlert = GenericUtility.isAuthorized(userName, "Delete Alerts");
        boolean permittedToViewAlert = GenericUtility.isAuthorized(userName, "Get Alert Details");
        
        if (orderByColumn != null)
        {
            request.setAttribute("orderByColumn",orderByColumn);
        }

        if (pageNumber == null)
        {
            long length = Long.parseLong(viewLength);
            
            long x = total / length;
            long y = total % length;
            if (y != 0)
            {
                x = x+1;
            }
            String pagenumber = String.valueOf(x);
            request.setAttribute("PAGENUMBER",pagenumber);
        }

        if (displayName == null || displayName.trim().equals(""))
        {
            try
                {
            displayName = AlarmViewUtil.getInstance().getDisplayName(userName,viewId);
                }
            catch(Exception e)
                {
                    System.out.println(e);
                }
        }

        
        request.setAttribute("RECORDS",new Long(total));
        request.setAttribute("isAscending",isAscending);
        request.setAttribute("viewId",viewId);
        request.setAttribute("viewLength",new Integer(viewLength));
        request.setAttribute("startIndex",startIndex);
        
        request.setAttribute("permittedToViewAlert",new Boolean(permittedToViewAlert));
        request.setAttribute("permittedToDeleteAlert",new Boolean(permittedToDeleteAlert));
        request.setAttribute("permittedToClearAlert",new Boolean(permittedToClearAlert));
        request.setAttribute("displayName",displayName);

        request.setAttribute("headerList",headerList);
        request.setAttribute("viewData",viewData);
        request.setAttribute("action","AlarmView.do");
    	request.setAttribute("PAGE_LENGTHS",list);
        request.setAttribute("CUSTOMIZE_COLUMNS_ACTION","AlarmColumnCustomizer.do");
        
        return mapping.findForward("AlarmSearchView");
    }
}
