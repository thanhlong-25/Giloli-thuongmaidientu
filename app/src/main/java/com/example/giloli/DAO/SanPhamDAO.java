package com.example.giloli.DAO;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.giloli.Activity.ChiTietDanhMucSanPham;
import com.example.giloli.Model.SanPham;
import com.example.giloli.fragment.TrangChuFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private DatabaseReference mDatabase;
    Context context;
    String maSanPham;
    public static List<SanPham> list = new ArrayList<SanPham>();

    public SanPhamDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("SanPham");
        this.context = context;
    }

    public List<SanPham> layhetSanPham() {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Sach object and use the values to update the UI
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham s = data.getValue(SanPham.class);
                    list.add(s);
                }
                TrangChuFragment.adapter_gridviewSanPham.notifyDataSetChanged(); }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(listener);
        return list;
    }
    public List<SanPham> layHetSanPhamTheoDm(String mL) {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Sach object and use the values to update the UI
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham s = data.getValue(SanPham.class);
                    list.add(s);
                }
                ChiTietDanhMucSanPham.adapter_gridviewSanPham.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        Query query = FirebaseDatabase.getInstance().getReference("SanPham")
                .orderByChild("maLoai").equalTo(mL);
        query.addListenerForSingleValueEvent(listener);
        return list;
    }
    public List<SanPham> layHetSanPhamTheoDm1(String mL) {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Sach object and use the values to update the UI
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham s = data.getValue(SanPham.class);
                    list.add(s);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        Query query = FirebaseDatabase.getInstance().getReference("SanPham")
                .orderByChild("maLoai").equalTo(mL);
        query.addListenerForSingleValueEvent(listener);
        return list;
    }
    public List<SanPham> layHetSanPhamTheoMSP(String maSanPham) {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Sach object and use the values to update the UI
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SanPham s = data.getValue(SanPham.class);
                    list.add(s);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        Query query = FirebaseDatabase.getInstance().getReference("SanPham")
                .orderByChild("maSanPham").equalTo(maSanPham);
        query.addListenerForSingleValueEvent(listener);
        return list;
    }
    public void capNhatSanPham(final SanPham sanPham) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("maSanPham").getValue(String.class).equals(sanPham.getMaSanPham())) {
                        maSanPham = data.getKey();
                        mDatabase.child(maSanPham).setValue(sanPham)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
                    } } }@Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



}
