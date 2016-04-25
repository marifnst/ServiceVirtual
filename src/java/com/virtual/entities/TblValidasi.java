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
@Table(name = "tbl_validasi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblValidasi.findAll", query = "SELECT t FROM TblValidasi t"),
    @NamedQuery(name = "TblValidasi.findById", query = "SELECT t FROM TblValidasi t WHERE t.id = :id"),
    @NamedQuery(name = "TblValidasi.findByIdThread", query = "SELECT t FROM TblValidasi t WHERE t.idThread = :idThread"),
    @NamedQuery(name = "TblValidasi.findByIdValidasi", query = "SELECT t FROM TblValidasi t WHERE t.idValidasi = :idValidasi"),
    @NamedQuery(name = "TblValidasi.findByStatusTransaksi", query = "SELECT t FROM TblValidasi t WHERE t.statusTransaksi = :statusTransaksi"),
    @NamedQuery(name = "TblValidasi.findByCreatedDate", query = "SELECT t FROM TblValidasi t WHERE t.createdDate = :createdDate")})
public class TblValidasi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ID_THREAD")
    private String idThread;
    @Basic(optional = false)
    @Column(name = "ID_VALIDASI")
    private String idValidasi;
    @Basic(optional = false)
    @Column(name = "STATUS_TRANSAKSI")
    private String statusTransaksi;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public TblValidasi() {
    }

    public TblValidasi(Integer id) {
        this.id = id;
    }

    public TblValidasi(Integer id, String idThread, String idValidasi, String statusTransaksi, Date createdDate) {
        this.id = id;
        this.idThread = idThread;
        this.idValidasi = idValidasi;
        this.statusTransaksi = statusTransaksi;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdThread() {
        return idThread;
    }

    public void setIdThread(String idThread) {
        this.idThread = idThread;
    }

    public String getIdValidasi() {
        return idValidasi;
    }

    public void setIdValidasi(String idValidasi) {
        this.idValidasi = idValidasi;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
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
        if (!(object instanceof TblValidasi)) {
            return false;
        }
        TblValidasi other = (TblValidasi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.virtual.entities.TblValidasi[ id=" + id + " ]";
    }
    
}
