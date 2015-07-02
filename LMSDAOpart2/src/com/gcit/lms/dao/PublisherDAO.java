package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO {

	public void create(Publisher pub) throws Exception {
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values(?, ?, ?)",
				new Object[] { pub.getPublisherName(), pub.getPublisherAddress(), pub.getPublisherPhone() });
	}

	public void update(Publisher pub) throws Exception {
		save("update tbl_publisher set publisherName = ?, publisherAddress, publisherPhone where publisherId = ?",
				new Object[] { pub.getPublisherName(), pub.getPublisherAddress(), pub.getPublisherPhone()});
	}

	public void delete(Publisher pub) throws Exception {
		save("delete from tbl_publisher where publisherId = ?",
				new Object[] { pub.getPublisherName(),pub.getPublisherAddress(), pub.getPublisherPhone()  });
	}

	public List<Publisher> readAll() throws Exception{
		return (List<Publisher>) read("select * from tbl_publisher", null);
		
	}

	public Publisher readOne(int publisherId) throws Exception {
		List<Publisher> publishers = (List<Publisher>) read("select * from tbl_publisher", new Object[] {publisherId});
		if(publishers!=null && publishers.size()>0){
			return publishers.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Publisher> publishers =  new ArrayList<Publisher>();
		
		while(rs.next()){
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));
			
			
			publishers.add(a);
		}
		return publishers;
	}
}
