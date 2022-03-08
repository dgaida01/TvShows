package com.gaida.exam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="tvshows")
public class TvShow {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message="is required!")
	private String title;
	
	@NotEmpty(message="is required!")
	private String network;
	
	@NotEmpty(message="is required!")
	private String description;
	
//*********************************************************************
//Database created and updated date time stamps
//*********************************************************************
		
		@Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    
	    private Date createdAt;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
		
//*********************************************************************
//Auto complete Date time stamps upon creation and update
//*********************************************************************
	   @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
    
    
    
	
//*********************************************************************
//Create RELATIONSHIPS HERE AS NEEDED
//*********************************************************************

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="person_id")
    private User person;
    
    @OneToMany(mappedBy="aShow",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Rating> showRatings;
    
 //*********************************************************************
// Constructors for User
//*********************************************************************

    public TvShow() {}
    
	public TvShow(String title,String network,String description, User person,
			List<com.gaida.exam.models.Rating> showRatings) {
		this.title = title;
		this.network = network;
		this.description = description;
		this.person = person;
		this.showRatings = showRatings;
	}
	
	
	public double avgRating() {
		double sum=0;
    	for(int i = 0 ; i<showRatings.size(); i++) {
    		sum +=showRatings.get(i).getScore();
    	}
    	 double result = sum/showRatings.size();
    	 return result;
		
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getPerson() {
		return person;
	}
	public void setPerson(User person) {
		this.person = person;
	}
	public List<Rating> getShowRatings() {
		return showRatings;
	}
	public void setShowRatings(List<Rating> showRatings) {
		this.showRatings = showRatings;
	}
    
    
    
    
	
	
	
}
