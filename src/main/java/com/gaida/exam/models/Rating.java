package com.gaida.exam.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="ratings")
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull (message="Cannot be blank")
	@Min(value=1 , message = "Rating must be at least 1")
	@Max(value=5, message ="Rating cannot be greater than 5")
	private double score;
	
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
	//Create one to many relation ship between ratings and Owners 
	//*********************************************************************
		    @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name="user_id")
		    private User owner;
		    
		    @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name="show_id")
		    private TvShow aShow;

 //*********************************************************************
 // Constructors for Ratings
 //*********************************************************************

		    public Rating () {}
			public Rating(double score,	User owner, TvShow aShow) {
				this.score = score;
				this.owner = owner;
				this.aShow = aShow;
			}
			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			public double getScore() {
				return score;
			}
			public void setScore(double score) {
				this.score = score;
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
			public User getOwner() {
				return owner;
			}
			public void setOwner(User owner) {
				this.owner = owner;
			}
			public TvShow getaShow() {
				return aShow;
			}
			public void setaShow(TvShow aShow) {
				this.aShow = aShow;
			}
			
			
			
			
}