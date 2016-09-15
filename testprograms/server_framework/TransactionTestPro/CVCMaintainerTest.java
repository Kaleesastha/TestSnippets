package txn;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.management.transaction.TransactionAPI;

public class CVCMaintainerTest extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        doGet(req, res);
    }

    private PrintWriter out = null;
    private TopoAPI tapi = null;
    private TransactionAPI txnAPI = null;

    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        out=res.getWriter();

        out.println("********** Testing STARTED for CVCMaintainerTest ********* \n");

        tapi = (TopoAPI)NmsUtil.getAPI("TopoAPI");
        txnAPI = NmsUtil.relapi.getTransactionAPI();

        //testcase001();
        //testcase002();
        //testcase003();
        //testcase004();
        //testcase005();
        //testcase006();
        //testcase007();
        //testcase008();
        //testcase009();
        //testcase010();
        //testcase011();
        //testcase012();
        //testcase013();
        testcase014();
        testcase015();
        testcase016();
        out.println("********** Testing COMPLETED for CVCMaintainerTest ********* \n");
    }

    private void testcase001()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-001 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("DB-TXN-CVC-001");
                mo.setType("Network");
                tapi.addObject(mo);
            }
            Thread.sleep(10000);
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-001 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-001 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-001 :: EXIT");
    }

    private void testcase002()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-002 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("DB-TXN-CVC-002");
                mo.setType("Network");
                tapi.addObject(mo);

                Thread.sleep(6000);

                ManagedObject mo1 = new ManagedObject();
                mo1.setName("DB-TXN-CVC-002.1");
                mo1.setType("Network");
                tapi.addObject(mo1);

                Thread.sleep(6000);

                ManagedObject mo2 = new ManagedObject();
                mo2.setName("DB-TXN-CVC-002.2");
                mo2.setType("Network");
                tapi.addObject(mo2);

                Thread.sleep(6000);

                ManagedObject mo3 = new ManagedObject();
                mo3.setName("DB-TXN-CVC-002.3");
                mo3.setType("Network");
                tapi.addObject(mo3);
                Thread.sleep(6000);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-002 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-002 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-002 :: EXIT");
    }

    private void testcase003()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-003 :: ENTER");
        try
        {
           ManagedObject mo = new ManagedObject();
           mo.setName("DB-TXN-CVC-003");
           mo.setType("Network");
           tapi.addObject(mo);

           Thread.sleep(20000);

            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo1 = tapi.getByName("DB-TXN-CVC-003");
                tapi.deleteObject(mo1);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-003 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-003 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-003 :: EXIT");
    }

    private void testcase004()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-004 :: ENTER");
        try
        {
            ManagedObject mo = new ManagedObject();
            mo.setName("DB-TXN-CVC-004");
            mo.setType("Network");
            tapi.addObject(mo);

            ManagedObject mo1 = new ManagedObject();
            mo1.setName("DB-TXN-CVC-004.1");
            mo1.setType("Network");
            tapi.addObject(mo1);

            ManagedObject mo2 = new ManagedObject();
            mo2.setName("DB-TXN-CVC-004.2");
            mo2.setType("Network");
            tapi.addObject(mo2);

            ManagedObject mo3 = new ManagedObject();
            mo3.setName("DB-TXN-CVC-004.3");
            mo3.setType("Network");
            tapi.addObject(mo3);

            Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo4 = tapi.getByName("DB-TXN-CVC-004");
                tapi.deleteObject(mo4);

                Thread.sleep(2000);

                ManagedObject mo5 = tapi.getByName("DB-TXN-CVC-004.1");
                tapi.deleteObject(mo5);

                Thread.sleep(2000);

                ManagedObject mo6 = tapi.getByName("DB-TXN-CVC-004.2");
                tapi.deleteObject(mo6);

                Thread.sleep(2000);

                ManagedObject mo7 = tapi.getByName("DB-TXN-CVC-004.3");
                tapi.deleteObject(mo7);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-004 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-004 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-004 :: EXIT");
    }

    private void testcase005()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-005 :: ENTER");
        try
        {
            ManagedObject mo = new ManagedObject();
            mo.setName("DB-TXN-CVC-005");
            mo.setType("Network");
            tapi.addObject(mo);

            ManagedObject mo1 = new ManagedObject();
            mo1.setName("DB-TXN-CVC-005.1");
            mo1.setType("Network");
            tapi.addObject(mo1);

            ManagedObject mo2 = new ManagedObject();
            mo2.setName("DB-TXN-CVC-005.2");
            mo2.setType("Network");
            tapi.addObject(mo2);

            ManagedObject mo3 = new ManagedObject();
            mo3.setName("DB-TXN-CVC-005.3");
            mo3.setType("Network");
            tapi.addObject(mo3);

            Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo4 = tapi.getByName("DB-TXN-CVC-005");
                mo4.setType("SUN");
                tapi.updateObject(mo4);

                Thread.sleep(2000);

                ManagedObject mo5 = tapi.getByName("DB-TXN-CVC-005.1");
                mo5.setType("SUN");
                tapi.updateObject(mo5);

                Thread.sleep(2000);

                ManagedObject mo6 = tapi.getByName("DB-TXN-CVC-005.2");
                mo6.setType("SUN");
                tapi.updateObject(mo6);

                Thread.sleep(2000);

                ManagedObject mo7 = tapi.getByName("DB-TXN-CVC-005.3");
                mo7.setType("SUN");
                tapi.updateObject(mo7);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-005 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-005 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-005 :: EXIT");
    }

    private void testcase006()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-006 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("DB-TXN-CVC-006");
                mo.setType("Network");
                tapi.addObject(mo);
            }
            txnAPI.rollback();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-006 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-006 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-006 :: EXIT");
    }

    private void testcase007()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-007 :: ENTER");
        try
        {
           ManagedObject mo = new ManagedObject();
           mo.setName("DB-TXN-CVC-007");
           mo.setType("Network");
           tapi.addObject(mo);

           Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo1 = tapi.getByName("DB-TXN-CVC-007");
                tapi.deleteObject(mo1);
            }
            try//kaschange
			            {
			                txnAPI.rollback();
			            }
			            catch(Exception ee)
			            {}

        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-007 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-007 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-007 :: EXIT");
    }

    private void testcase008()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-008 :: ENTER");
        try
        {
           ManagedObject mo = new ManagedObject();
           mo.setName("DB-TXN-CVC-008");
           mo.setType("Network");
           tapi.addObject(mo);

           Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TIP, -1);
            {
                ManagedObject mo1 = tapi.getByName("DB-TXN-CVC-008");
                mo1.setType("kasNetwork");
                tapi.updateObject(mo1);
            }
           try//kaschange
		               {
		                   txnAPI.rollback();
		               }
		               catch(Exception ee)
		               {}

        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-008 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-008 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-008 :: EXIT");
    }

    private void testcase009()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-009 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("DB-TXN-CVC-009");
                mo.setType("Network");
                tapi.addObject(mo);
            }
            Thread.sleep(10000);
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-009 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-009 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-009 :: EXIT");
    }

    private void testcase010()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-010 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("DB-TXN-CVC-010");
                mo.setType("Network");
                tapi.addObject(mo);

                Thread.sleep(2000);

                ManagedObject mo1 = new ManagedObject();
                mo1.setName("DB-TXN-CVC-010.1");
                mo1.setType("Network");
                tapi.addObject(mo1);

                Thread.sleep(2000);

                ManagedObject mo2 = new ManagedObject();
                mo2.setName("DB-TXN-CVC-010.2");
                mo2.setType("Network");
                tapi.addObject(mo2);

                Thread.sleep(2000);

                ManagedObject mo3 = new ManagedObject();
                mo3.setName("DB-TXN-CVC-010.3");
                mo3.setType("Network");
                tapi.addObject(mo3);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-010 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-010 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-010 :: EXIT");
    }

    private void testcase011()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-011 :: ENTER");
        try
        {
           ManagedObject mo = new ManagedObject();
           mo.setName("DB-TXN-CVC-011");
           mo.setType("Network");
           tapi.addObject(mo);

           Thread.sleep(10000);

            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo1 = tapi.getByName("DB-TXN-CVC-011");
                tapi.deleteObject(mo1);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-011 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-011 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-011 :: EXIT");
    }

    private void testcase012()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-012 :: ENTER");
        try
        {
            ManagedObject mo = new ManagedObject();
            mo.setName("DB-TXN-CVC-012");
            mo.setType("Network");
            tapi.addObject(mo);

            ManagedObject mo1 = new ManagedObject();
            mo1.setName("DB-TXN-CVC-012.1");
            mo1.setType("Network");
            tapi.addObject(mo1);

            ManagedObject mo2 = new ManagedObject();
            mo2.setName("DB-TXN-CVC-012.2");
            mo2.setType("Network");
            tapi.addObject(mo2);

            ManagedObject mo3 = new ManagedObject();
            mo3.setName("DB-TXN-CVC-012.3");
            mo3.setType("Network");
            tapi.addObject(mo3);

            Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo4 = tapi.getByName("DB-TXN-CVC-012");
                tapi.deleteObject(mo4);

                Thread.sleep(2000);

                ManagedObject mo5 = tapi.getByName("DB-TXN-CVC-012.1");
                tapi.deleteObject(mo5);

                Thread.sleep(2000);

                ManagedObject mo6 = tapi.getByName("DB-TXN-CVC-012.2");
                tapi.deleteObject(mo6);

                Thread.sleep(2000);

                ManagedObject mo7 = tapi.getByName("DB-TXN-CVC-012.3");
                tapi.deleteObject(mo7);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-012 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-012 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-012 :: EXIT");
    }

    private void testcase013()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-013 :: ENTER");
        try
        {
            ManagedObject mo = new ManagedObject();
            mo.setName("DB-TXN-CVC-013");
            mo.setType("Network");
            tapi.addObject(mo);

            ManagedObject mo1 = new ManagedObject();
            mo1.setName("DB-TXN-CVC-013.1");
            mo1.setType("Network");
            tapi.addObject(mo1);

            ManagedObject mo2 = new ManagedObject();
            mo2.setName("DB-TXN-CVC-013.2");
            mo2.setType("Test");
            tapi.addObject(mo2);

            ManagedObject mo3 = new ManagedObject();
            mo3.setName("DB-TXN-CVC-013.3");
            mo3.setType("Network");
            tapi.addObject(mo3);

            Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo4 = tapi.getByName("DB-TXN-CVC-013");
                mo4.setType("SUN");
                tapi.updateObject(mo4);

                Thread.sleep(2000);

                ManagedObject mo5 = tapi.getByName("DB-TXN-CVC-013.1");
                mo5.setType("SUN");
                tapi.updateObject(mo5);

                Thread.sleep(2000);

                ManagedObject mo6 = tapi.getByName("DB-TXN-CVC-013.2");
                mo6.setType("SUN");
                tapi.updateObject(mo6);

                Thread.sleep(2000);

                ManagedObject mo7 = tapi.getByName("DB-TXN-CVC-013.3");
                mo7.setType("SUN");
                tapi.updateObject(mo7);
            }
            txnAPI.commit();
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-013 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-013 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-013 :: EXIT");
    }

    private void testcase014()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-014 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("DB-TXN-CVC-014");
                mo.setType("Network");
                tapi.addObject(mo);
            }
           try//kaschange
		               {
		                   txnAPI.rollback();
		               }
		               catch(Exception ee)
            {}
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-014 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-014 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-014 :: EXIT");
    }

    private void testcase015()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-015 :: ENTER");
        try
        {
           ManagedObject mo = new ManagedObject();
           mo.setName("DB-TXN-CVC-015");
           mo.setType("Network");
           tapi.addObject(mo);

           Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo1 = tapi.getByName("DB-TXN-CVC-015");
                tapi.deleteObject(mo1);
            }
            try//kaschange
			            {
			                txnAPI.rollback();
			            }
			            catch(Exception ee)
            {}
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-015 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-015 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-015 :: EXIT");
    }

    private void testcase016()
    {
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-016 :: ENTER");
        try
        {
           ManagedObject mo = new ManagedObject();
           mo.setName("DB-TXN-CVC-016");
           mo.setType("Network");
           tapi.addObject(mo);

           Thread.sleep(5000);

            txnAPI.begin(TransactionAPI.NOTIFY_TC, -1);
            {
                ManagedObject mo1 = tapi.getByName("DB-TXN-CVC-016");
                mo1.setType("Network");
                tapi.updateObject(mo1);
            }
            try//kaschange
					               {
					                   txnAPI.rollback();
					               }
					               catch(Exception ee)
            {}
        }
        catch(Exception e)
        {
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-016 :: FAILED");
            out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-016 ::"
                        + " Exception Recieved :: \n" + e);
            e.printStackTrace();
            try
            {
                txnAPI.rollback();
            }
            catch(Exception ee)
            {}
        }
        out.println(" CVCMaintainerTest Testcase : DB-TXN-CVC-016 :: EXIT");
    }


}
