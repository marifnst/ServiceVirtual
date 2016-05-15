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
@Table(name = "tbl_transaksi_bank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTransaksiBank.findAll", query = "SELECT t FROM TblTransaksiBank t"),
    @NamedQuery(name = "TblTransaksiBank.findById", query = "SELECT t FROM TblTransaksiBank t WHERE t.id = :id"),
    @NamedQuery(name = "TblTransaksiBank.findByIdValidasi", query = "SELECT t FROM TblTransaksiBank t WHERE t.idValidasi = :idValidasi"),
    @NamedQuery(name = "TblTransaksiBank.findByIdTransaksi", query = "SELECT t FROM TblTransaksiBank t WHERE t.idTransaksi = :idTransaksi"),
    @NamedQuery(name = "TblTransaksiBank.findByNoKartuKredit", query = "SELECT t FROM TblTransaksiBank t WHERE t.noKartuKredit = :noKartuKredit"),
    @NamedQuery(name = "TblTransaksiBank.findByStatusTransaksi", query = "SELECT t FROM TblTransaksiBank t WHERE t.statusTransaksi = :statusTransaksi"),
    @NamedQuery(name = "TblTransaksiBank.findByCreatedDate", query = "SELECT t FROM TblTransaksiBank t WHERE t.createdDate = :createdDate")})
public class TblTransaksiBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ID_VALIDASI")
    private String idValidasi;
    @Basic(optional = false)
    @Column(name = "ID_TRANSAKSI")
    private String idTransaksi;
    @Basic(optional = false)
    @Column(name = "NO_KARTU_KREDIT")
    private String noKartuKredit;
    @Basic(optional = false)
    @Column(name = "STATUS_TRANSAKSI")
    private String statusTransaksi;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public TblTransaksiBank() {
    }

    public TblTransaksiBank(Integer id) {
        this.id = id;
    }

    public TblTransaksiBank(Integer id, String idValidasi, String idTransaksi, String noKartuKredit, String statusTransaksi, Date createdDate) {
        this.id = id;
        this.idValidasi = idValidasi;
        this.idTransaksi = idTransaksi;
        this.noKartuKredit = noKartuKredit;
        this.statusTransaksi = statusTransaksi;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdValidasi() {
        return idValidasi;
    }

    public void setIdValidasi(String idValidasi) {
        this.idValidasi = idValidasi;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNoKartuKredit() {
        return noKartuKredit;
    }

    public void setNoKartuKredit(String noKartuKredit) {
        this.noKartuKredit = noKartuKredit;
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
        if (!(object instanceof TblTransaksiBank)) {
            return false;
        }
        TblTransaksiBank other = (TblTransaksiBank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.virtual.entities.TblTransaksiBank[ id=" + id + " ]";
    }
    
}
