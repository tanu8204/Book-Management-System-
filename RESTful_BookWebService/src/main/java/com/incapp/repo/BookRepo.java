package com.incapp.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Book;

import jakarta.servlet.http.Part;

@Repository
public class BookRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String addBook(Book b, MultipartFile image) {
		try {
			String query = "insert into books values(?,?,?,?,?)";
				jdbcTemplate.update(query,new Object[] { b.getName(), b.getPrice(), b.getAname(), b.getPname(), image.getInputStream() });
			return "Book Added Successfully!";
		}catch (org.springframework.dao.DuplicateKeyException e) {
			return "Book Already Exist!";
		}catch (Exception ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
	public String addBook(Book b) {
		try {
			String query = "insert into books (name,price,aname,pname) values(?,?,?,?)";
			jdbcTemplate.update(query, new Object[] { b.getName(), b.getPrice(), b.getAname(), b.getPname() });
			return "Book Added Successfully!";
		}catch (org.springframework.dao.DuplicateKeyException e) {
			return "Book Already Exist!";
		}catch (Exception ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}

	public String doUpdateBook(Book b, String oldName, Part image) {
		try {
			if (image.getSize() == 0) {
				String query = "update books set name=? , price=? , aname=? , pname=?  where name=?";
				jdbcTemplate.update(query,
						new Object[] { b.getName(), b.getPrice(), b.getAname(), b.getPname(), oldName });
			} else {
				String query = "update books set name=? , price=? , aname=? , pname=?, image=?  where name=?";
				jdbcTemplate.update(query, new Object[] { b.getName(), b.getPrice(), b.getAname(), b.getPname(),
						image.getInputStream(), oldName });
			}

			return "Book Updated Successfully";
		} catch (Exception ex) {
			return "Book Already Exist!";
		}
	}

	// get all books
	public List<Book> getBooks() {
		class BookMapper implements RowMapper {
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book b = new Book();
				b.setName(rs.getString("name"));
				b.setPrice(rs.getInt("price"));
				b.setAname(rs.getString("aname"));
				b.setPname(rs.getString("pname"));
				return b;
			}
		}
		try {
			final String query = "select * from books";
			List<Book> b = jdbcTemplate.query(query, new BookMapper());
			return b;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	// get all books like name
	public List<Book> getBooks(String name) {
		class BookMapper implements RowMapper {
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book b = new Book();
				b.setName(rs.getString("name"));
				b.setPrice(rs.getInt("price"));
				b.setAname(rs.getString("aname"));
				b.setPname(rs.getString("pname"));
				return b;
			}
		}
		try {
			final String query = "select * from books where name like '%" + name + "%'";
			List<Book> b = jdbcTemplate.query(query, new BookMapper());
			return b;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	public Book getBook(String name) {
		class BookMapper implements RowMapper {
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book b = new Book();
				b.setName(rs.getString("name"));
				b.setPrice(rs.getInt("price"));
				b.setAname(rs.getString("aname"));
				b.setPname(rs.getString("pname"));
				return b;
			}
		}
		try {
			final String query = "select * from books where name = '" + name + "'";
			Book b = (Book) jdbcTemplate.queryForObject(query, new BookMapper());
			return b;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	public byte[] getBookImage(String name) {
		class BookMapper implements RowMapper {
			public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getBytes("image");
			}
		}
		try {
			final String query = "select image from books where name = '" + name + "'";
			byte[] image = (byte[]) jdbcTemplate.queryForObject(query, new BookMapper());
			return image;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	// delete book by name
	public boolean deleteBook(String name) {
		String query = "delete from books where name = '" + name + "'";
		int x = jdbcTemplate.update(query);
		if (x != 0)
			return true;
		else
			return false;
	}
}
