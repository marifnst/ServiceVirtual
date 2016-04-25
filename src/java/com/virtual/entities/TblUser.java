/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emerio
 */
@Entity
@Table(name = "tbl_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t"),
    @NamedQuery(name = "TblUser.findById", query = "SELECT t FROM TblUser t WHERE t.id = :id"),
    @NamedQuery(name = "TblUser.findByNama", query = "SELECT t FROM TblUser t WHERE t.nama = :nama"),
    @NamedQuery(name = "TblUser.findByPassword", query = "SELECT t FROM TblUser t WHERE t.password = :password"),
    @NamedQuery(name = "TblUser.findByNoKartuKredit", query = "SELECT t FROM TblUser t WHERE t.noKartuKredit = :noKartuKredit"),
    @NamedQuery(name = "TblUser.findByCreatedDate", query = "SELECT t FROM TblUser t WHERE t.createdDate = :createdDate")})
public class TblUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAMA")
    private String nama;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "NO_KARTU_KREDIT")
    private String noKartuKredit;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public TblUser() {
    }

    public TblUser(Integer id) {
        this.id = id;
    }

    public TblUser(Integer id, String nama, String password, String noKartuKredit, Date createdDate) {
        this.id = id;
        this.nama = nama;
        this.password = password;
        this.noKartuKredit = noKartuKredit;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoKartuKredit() {
        return noKartuKredit;
    }

    public void setNoKartuKredit(String noKartuKredit) {
        this.noKartuKredit = noKartuKredit;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.virtual.entities.TblUser[ id=" + id + " ]";
    }
    
}
