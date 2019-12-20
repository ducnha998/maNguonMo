    package com.nguyenducnha.doan_nuocngot;

    import androidx.room.Entity;
    import androidx.room.PrimaryKey;

    import java.io.Serializable;

    @Entity
    public class NuocNgot implements Serializable {@PrimaryKey(autoGenerate = true)
    private int maSP;
    private String tenSP;
    private String phanLoai;
    private int gia;
    private String nhaSX;
    private String xuatXu;
    private int hinhanh;

        public NuocNgot(){

        }

        public NuocNgot(int maSP, String tenSP, String phanLoai, int gia, String nhaSX, String xuatXu, int hinhanh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.phanLoai = phanLoai;
        this.gia = gia;
        this.nhaSX = nhaSX;
        this.xuatXu = xuatXu;
        this.hinhanh = hinhanh;
        }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getNhaSX() {
        return nhaSX;
    }

    public void setNhaSX(String nhaSX) {
        this.nhaSX = nhaSX;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

        public int getHinhanh() {
            return hinhanh;
        }

        public void setHinhanh(int hinhanh) {
            this.hinhanh = hinhanh;
        }
    }
