package queryBean;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import plannerManagement.application.AppointmentBean;
import plannerManagement.application.PlannerBean;
import plannerManagement.storage.PlannerQueryBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TC_CreateNewSchedule {
    @Mock
    PlannerBean plannerBean;
    @Mock
    ArrayList<AppointmentBean> appointments;
    @Mock
    AppointmentBean appointmentBean;
    @InjectMocks
    PlannerQueryBean plannerQueryBean;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCorrectNewSchedule(){
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("04/02/2023");
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse("11/02/2023");;
            appointmentBean = new AppointmentBean();
            appointments = new ArrayList<>();
            appointments.add(appointmentBean);
            ObjectId id = new ObjectId();
            when(plannerBean.getId()).thenReturn(String.valueOf(id));
            when(plannerBean.getStartDate()).thenReturn(startDate);
            when(plannerBean.getEndDate()).thenReturn(endDate);
            when(plannerBean.getAppointments()).thenReturn(appointments);
            assertEquals(true, plannerQueryBean.insertDocument(plannerBean));
            plannerQueryBean.deleteDocument("_id", plannerBean.getId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testCreateScheduleWithNoAppointments(){
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("04/02/2023");
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse("11/02/2023");;
            appointmentBean = new AppointmentBean();
            appointments = new ArrayList<>();
            ObjectId id = new ObjectId();
            when(plannerBean.getId()).thenReturn(String.valueOf(id));
            when(plannerBean.getStartDate()).thenReturn(startDate);
            when(plannerBean.getEndDate()).thenReturn(endDate);
            when(plannerBean.getAppointments()).thenReturn(appointments);
            assertEquals(false, plannerQueryBean.insertDocument(plannerBean));
            plannerQueryBean.deleteDocument("_id", plannerBean.getId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
