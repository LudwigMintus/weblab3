package beans;



import model.Point;
import model.DB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@ApplicationScoped
public class MainBean {

    private Point newData =new Point();
    private String shape_name;
    private List<Point> dataList = new ArrayList<Point>();
    private Integer counter = 0;
    private Integer count=0;
    public String getShape_name() {
        return shape_name;
    }

    public void addData() {
        newData.setStatus(false);
        dataList.add(newData);
        newData = new Point();
    }

    public Point getNewData() {
        if (this.newData == null) {
            this.newData = new Point(.0, .0, 1.0, false);
        }
        return newData;
    }

    public String updateData() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Point prev = this.newData;
        this.newData = new Point(Double.parseDouble(request.getParameter("form2:x1")), Double.parseDouble(request.getParameter("form2:y1")), Double.parseDouble(request.getParameter("form2:r1")), false);
        createData();
        this.newData = prev;
        return "redirectURL";
    }

    public void setNewData(Point newData) {
        this.newData = newData;
    }

    public List<Point> getDataList() {
        if (newData != null && newData.getR() != null) {
            DB DBInstance = DB.getInstance();
            return DBInstance.getAll(newData.getR());
        }
        return new ArrayList<Point>();
    }

    public void setDataList(List<Point> dataList) {
        this.dataList = dataList;
    }

    public void clear() {
        dataList.clear();
    }

    public void createData() {
        DB DBInstance = DB.getInstance();
        DBInstance.add(this.newData);
    }

    public void clearData() {
        DB DBInstance = DB.getInstance();
        DBInstance.clear();
    }

    public Integer getCounter() {
        if (counter > dataList.size()) {
            counter = 0;
        }
        return counter++;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Point get() {
        if (dataList.size() == 0) {
            return null;
        } else {
            if (counter == dataList.size()) {
                counter = 0;
            }
            counter = counter + 1;
            return dataList.get(counter - 1);
        }
    }

}