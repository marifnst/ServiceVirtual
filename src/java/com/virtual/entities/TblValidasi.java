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
    @NamedQuery(name = "TblValidasi.findByCreatedDate", query = "SELECT t FROM TblValidasi t WHERE t.createdDate = :createdDate"),
    @NamedQuery(name = "TblValidasi.findByNama", query = "SELECT t FROM TblValidasi t WHERE t.nama = :nama"),
    @NamedQuery(name = "TblValidasi.findByIsUserValid", query = "SELECT t FROM TblValidasi t WHERE t.isUserValid = :isUserValid"),
    @NamedQuery(name = "TblValidasi.findByElapsedTimeValidation", query = "SELECT t FROM TblValidasi t WHERE t.elapsedTimeValidation = :elapsedTimeValidation"),
    @NamedQuery(name = "TblValidasi.findByElapsedTimeBank", query = "SELECT t FROM TblValidasi t WHERE t.elapsedTimeBank = :elapsedTimeBank"),
    @NamedQuery(name = "TblValidasi.findByElapsedTimeKurir", query = "SELECT t FROM TblValidasi t WHERE t.elapsedTimeKurir = :elapsedTimeKurir"),
    @NamedQuery(name = "TblValidasi.findByNoSimulasi", query = "SELECT t FROM TblValidasi t WHERE t.noSimulasi = :noSimulasi")})
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
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "IS_USER_VALID")
    private String isUserValid;
    @Column(name = "ELAPSED_TIME_VALIDATION")
    private String elapsedTimeValidation;
    @Column(name = "ELAPSED_TIME_BANK")
    private String elapsedTimeBank;
    @Column(name = "ELAPSED_TIME_KURIR")
    private String elapsedTimeKurir;
    @Column(name = "NO_SIMULASI")
    private Integer noSimulasi;

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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIsUserValid() {
        return isUserValid;
    }

    public void setIsUserValid(String isUserValid) {
        this.isUserValid = isUserValid;
    }

    public String getElapsedTimeValidation() {
        return elapsedTimeValidation;
    }

    public void setElapsedTimeValidation(String elapsedTimeValidation) {
        this.elapsedTimeValidation = elapsedTimeValidation;
    }

    public String getElapsedTimeBank() {
        return elapsedTimeBank;
    }

    public void setElapsedTimeBank(String elapsedTimeBank) {
        this.elapsedTimeBank = elapsedTimeBank;
    }

    public String getElapsedTimeKurir() {
        return elapsedTimeKurir;
    }

    public void setElapsedTimeKurir(String elapsedTimeKurir) {
        this.elapsedTimeKurir = elapsedTimeKurir;
    }

    public Integer getNoSimulasi() {
        return noSimulasi;
    }

    public void setNoSimulasi(Integer noSimulasi) {
        this.noSimulasi = noSimulasi;
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
