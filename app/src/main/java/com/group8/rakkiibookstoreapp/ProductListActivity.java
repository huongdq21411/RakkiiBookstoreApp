package com.group8.rakkiibookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.group8.rakkiibookstoreapp.adapter.BookListAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityProductListBinding;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductListActivity extends AppCompatActivity {

    ActivityProductListBinding binding;
    private ArrayList<BookList> list1 = new ArrayList<>();
    private ArrayList<BookList> list2 = new ArrayList<>();
    private ArrayList<BookList> list3 = new ArrayList<>();
    private ArrayList<BookList> list4 = new ArrayList<>();
    private ArrayList<BookList> list5 = new ArrayList<>();
    private ArrayList<BookList> listAll = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String cat = getIntent().getStringExtra("category");
        if (cat != null) {
            createList(cat);
        }
        binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, ProductListActivity.class);
                intent.putExtra("category", "cat1");
                startActivity(intent);
                finish();
            }
        });
        binding.imvBack.setOnClickListener(v -> finish());

    }
    public void createList(String cat) {
        if (cat.equals("cat1")) {
            addToList1();
            binding.txtCategoryName.setText("Văn học");
            binding.rvBooks.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            binding.rvBooks.setAdapter(new BookListAdapter(list1));
            binding.btnFilter.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    showFilterOptionsDialog();
                }

                private void showFilterOptionsDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn cách lọc");

                    String[] filterOptions = {"Lọc theo tiêu đề từ A đến Z", "Lọc theo tiêu đề từ Z đến A", "Lọc theo giá"};
                    builder.setSingleChoiceItems(filterOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByTitleAlphabetically(true);
                                break;
                            case 1:
                                sortByTitleAlphabetically(false);
                                break;
                            case 2:
                                showAutomaticFilterDialog();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByTitleAlphabetically(boolean ascending) {
                    Collections.sort(list1, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare titles alphabetically
                            if (ascending) {
                                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
                            } else {
                                return book2.getTitle().compareToIgnoreCase(book1.getTitle());
                            }
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void showAutomaticFilterDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn kiểu sắp xếp");

                    String[] sortOptions = {"Giá thấp đến cao", "Giá cao đến thấp"};
                    builder.setSingleChoiceItems(sortOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByPriceAscending();
                                break;
                            case 1:
                                sortByPriceDescending();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByPriceAscending() {
                    Collections.sort(list1, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in ascending order
                            return Double.compare(book1.getPrice(), book2.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void sortByPriceDescending() {
                    Collections.sort(list1, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in descending order
                            return Double.compare(book2.getPrice(), book1.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }
            });
        } else if (cat.equals("cat2")) {
            addToList2();
            binding.txtCategoryName.setText("Kinh tế");
            binding.rvBooks.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            binding.rvBooks.setAdapter(new BookListAdapter(list2));
            binding.btnFilter.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    showFilterOptionsDialog();
                }

                private void showFilterOptionsDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn cách lọc");

                    String[] filterOptions = {"Lọc theo tiêu đề từ A đến Z", "Lọc theo tiêu đề từ Z đến A", "Lọc theo giá"};
                    builder.setSingleChoiceItems(filterOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByTitleAlphabetically(true);
                                break;
                            case 1:
                                sortByTitleAlphabetically(false);
                                break;
                            case 2:
                                showAutomaticFilterDialog();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByTitleAlphabetically(boolean ascending) {
                    Collections.sort(list2, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare titles alphabetically
                            if (ascending) {
                                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
                            } else {
                                return book2.getTitle().compareToIgnoreCase(book1.getTitle());
                            }
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void showAutomaticFilterDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn kiểu sắp xếp");

                    String[] sortOptions = {"Giá thấp đến cao", "Giá cao đến thấp"};
                    builder.setSingleChoiceItems(sortOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByPriceAscending();
                                break;
                            case 1:
                                sortByPriceDescending();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByPriceAscending() {
                    Collections.sort(list2, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in ascending order
                            return Double.compare(book1.getPrice(), book2.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void sortByPriceDescending() {
                    Collections.sort(list2, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in descending order
                            return Double.compare(book2.getPrice(), book1.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }
            });
        } else if (cat.equals("cat3")) {
            addToList3();
            binding.txtCategoryName.setText("Tâm lý - Kỹ Năng");
            binding.rvBooks.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            binding.rvBooks.setAdapter(new BookListAdapter(list3));
            binding.btnFilter.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    showFilterOptionsDialog();
                }

                private void showFilterOptionsDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn cách lọc");

                    String[] filterOptions = {"Lọc theo tiêu đề từ A đến Z", "Lọc theo tiêu đề từ Z đến A", "Lọc theo giá"};
                    builder.setSingleChoiceItems(filterOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByTitleAlphabetically(true);
                                break;
                            case 1:
                                sortByTitleAlphabetically(false);
                                break;
                            case 2:
                                showAutomaticFilterDialog();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByTitleAlphabetically(boolean ascending) {
                    Collections.sort(list3, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare titles alphabetically
                            if (ascending) {
                                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
                            } else {
                                return book2.getTitle().compareToIgnoreCase(book1.getTitle());
                            }
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void showAutomaticFilterDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn kiểu sắp xếp");

                    String[] sortOptions = {"Giá thấp đến cao", "Giá cao đến thấp"};
                    builder.setSingleChoiceItems(sortOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByPriceAscending();
                                break;
                            case 1:
                                sortByPriceDescending();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByPriceAscending() {
                    Collections.sort(list3, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in ascending order
                            return Double.compare(book1.getPrice(), book2.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void sortByPriceDescending() {
                    Collections.sort(list3, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in descending order
                            return Double.compare(book2.getPrice(), book1.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }
            });
        } else if (cat.equals("cat4")) {
            addToList4();
            binding.txtCategoryName.setText("Sách thiếu nhi");
            binding.rvBooks.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            binding.rvBooks.setAdapter(new BookListAdapter(list4));
            binding.btnFilter.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    showFilterOptionsDialog();
                }

                private void showFilterOptionsDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn cách lọc");

                    String[] filterOptions = {"Lọc theo tiêu đề từ A đến Z", "Lọc theo tiêu đề từ Z đến A", "Lọc theo giá"};
                    builder.setSingleChoiceItems(filterOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByTitleAlphabetically(true);
                                break;
                            case 1:
                                sortByTitleAlphabetically(false);
                                break;
                            case 2:
                                showAutomaticFilterDialog();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByTitleAlphabetically(boolean ascending) {
                    Collections.sort(list4, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare titles alphabetically
                            if (ascending) {
                                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
                            } else {
                                return book2.getTitle().compareToIgnoreCase(book1.getTitle());
                            }
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void showAutomaticFilterDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn kiểu sắp xếp");

                    String[] sortOptions = {"Giá thấp đến cao", "Giá cao đến thấp"};
                    builder.setSingleChoiceItems(sortOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByPriceAscending();
                                break;
                            case 1:
                                sortByPriceDescending();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByPriceAscending() {
                    Collections.sort(list4, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in ascending order
                            return Double.compare(book1.getPrice(), book2.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void sortByPriceDescending() {
                    Collections.sort(list4, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in descending order
                            return Double.compare(book2.getPrice(), book1.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }
            });
        } else if (cat.equals("cat5")) {
            addToList5();
            binding.txtCategoryName.setText("Giáo khoa - Tham khảo");
            binding.rvBooks.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            binding.rvBooks.setAdapter(new BookListAdapter(list5));
            binding.btnFilter.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    showFilterOptionsDialog();
                }

                private void showFilterOptionsDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn cách lọc");

                    String[] filterOptions = {"Lọc theo tiêu đề từ A đến Z", "Lọc theo tiêu đề từ Z đến A", "Lọc theo giá"};
                    builder.setSingleChoiceItems(filterOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByTitleAlphabetically(true);
                                break;
                            case 1:
                                sortByTitleAlphabetically(false);
                                break;
                            case 2:
                                showAutomaticFilterDialog();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByTitleAlphabetically(boolean ascending) {
                    Collections.sort(list5, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare titles alphabetically
                            if (ascending) {
                                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
                            } else {
                                return book2.getTitle().compareToIgnoreCase(book1.getTitle());
                            }
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void showAutomaticFilterDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn kiểu sắp xếp");

                    String[] sortOptions = {"Giá thấp đến cao", "Giá cao đến thấp"};
                    builder.setSingleChoiceItems(sortOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByPriceAscending();
                                break;
                            case 1:
                                sortByPriceDescending();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByPriceAscending() {
                    Collections.sort(list5, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in ascending order
                            return Double.compare(book1.getPrice(), book2.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void sortByPriceDescending() {
                    Collections.sort(list5, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in descending order
                            return Double.compare(book2.getPrice(), book1.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }
            });
        } else if (cat.equals("nocat")) {
            addToList1(); addToList2(); addToList3(); addToList4(); addToList5();
            listAll.addAll(list1);
            listAll.addAll(list2);
            listAll.addAll(list3);
            listAll.addAll(list4);
            listAll.addAll(list5);
            binding.txtCategoryName.setText("Danh sách sản phẩm");
            binding.rvBooks.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false));
            binding.rvBooks.setAdapter(new BookListAdapter(listAll));
            binding.btnFilter.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    showFilterOptionsDialog();
                }

                private void showFilterOptionsDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn cách lọc");

                    String[] filterOptions = {"Lọc theo tiêu đề từ A đến Z", "Lọc theo tiêu đề từ Z đến A", "Lọc theo giá"};
                    builder.setSingleChoiceItems(filterOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByTitleAlphabetically(true);
                                break;
                            case 1:
                                sortByTitleAlphabetically(false);
                                break;
                            case 2:
                                showAutomaticFilterDialog();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByTitleAlphabetically(boolean ascending) {
                    Collections.sort(listAll, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare titles alphabetically
                            if (ascending) {
                                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
                            } else {
                                return book2.getTitle().compareToIgnoreCase(book1.getTitle());
                            }
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void showAutomaticFilterDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this,R.style.CustomDialogTheme);
                    builder.setTitle("Chọn kiểu sắp xếp");

                    String[] sortOptions = {"Giá thấp đến cao", "Giá cao đến thấp"};
                    builder.setSingleChoiceItems(sortOptions, -1, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                sortByPriceAscending();
                                break;
                            case 1:
                                sortByPriceDescending();
                                break;
                        }
                        dialog.dismiss();
                    });

                    builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);
                }

                private void sortByPriceAscending() {
                    Collections.sort(listAll, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in ascending order
                            return Double.compare(book1.getPrice(), book2.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }

                private void sortByPriceDescending() {
                    Collections.sort(listAll, new Comparator<BookList>() {
                        @Override
                        public int compare(BookList book1, BookList book2) {
                            // Compare prices in descending order
                            return Double.compare(book2.getPrice(), book1.getPrice());
                        }
                    });

                    // Update the RecyclerView adapter with the sorted list
                    binding.rvBooks.getAdapter().notifyDataSetChanged();
                }
            });
        }
    }

    public void addToList1() {
        list1.add(new BookList(1, "Văn học", "José Mauro de Vasconcelos","NXB Nhà Văn","Cây cam ngọt của tôi", "item_1", 7, 4, 108000, "“Vị chua chát của cái nghèo hòa trộn với vị ngọt ngào khi khám phá ra những điều khiến cuộc đời này đáng số một tác phẩm kinh điển của Brazil.” - Booklist\nHãy làm quen với Zezé, cậu bé tinh nghịch siêu hạng đồng thời cũng đáng yêu bậc nhất, với ước mơ lớn lên trở thành nhà thơ cổ thắt nơ bướm. Chẳng phải ai cũng công nhận khoản “đáng yêu” kia đâu nhé. Bởi vì, ở cái xóm ngoại ô nghèo ấy, nỗi khắc khổ bủa vây đã che mờ mắt người ta trước trái tim thiện lương cùng trí tưởng tượng tuyệt vời của cậu bé con năm tuổi."));
        list1.add(new BookList(2, "Văn học", "Diệp Lạc Vô Tâm","NXB Thanh Niên","Mãi mãi là bao xa - Tái bản 2019", "item_2", 3, 5, 94000, "Em là cây hoa loa kèn hoang dã mãi mãi chỉ vì chính mình mà nở hoa, rời khỏi đất mẹ là cái giá phải trả khi yêu anh.\"\"\nBạch Lăng Lăng, nữ sinh khoa Điện khí, trẻ trung, xinh đẹp và rất tự hào khi quen được một người bạn lý tưởng qua mạng, chàng du học sinh của một trường nổi tiếng của Mỹ, người mang biệt danh “nhà khoa học”: Mãi Mãi Là Bao Xa. Qua những cuộc chuyện trò trên QQ, Lăng Lăng đã gắn bó với chàng trai đó lúc nào cô cũng không hay, cảm xúc lớn dần, sự chia sẻ lớn dần và đến một ngày cô phát hiện ra mình đã yêu người con trai “tài giỏi” và không một chút khuyết điểm ấy."));
        list1.add(new BookList(3, "Văn học", "Đường Tửu Khanh",	"NXB Hà Nội","Thương tiến tửu - Tập 1", "item_3", 6, 3, 127000, "Vận mệnh đã muốn ta suốt kiếp chôn chân tại chốn này, nhưng số mệnh ấy nào phải con đường ta lựa chọn. Cát vàng chôn vùi huynh đệ ta, ta không muốn tiếp tục thần phục số mệnh hư vô nữa. Thánh chỉ không cứu được sĩ tốt của ta, triều đình không nuôi nổi chiến mã của ta, ta không muốn liều mạng vì những thứ đó nữa. Ta muốn băng qua núi xanh kia, ta muốn đánh một trận, vì chính mình."));
        list1.add(new BookList(4, "Văn học", "Higashino Keigo",	"NXB Hội Nhà Văn","Điều kỳ diệu của tiệm tạp hóa Namiya", "item_4", 8, 4.5, 63000, "Một đêm vội vã lẩn trốn sau phi vụ khoắng đồ nhà người, Atsuya, Shota và Kouhei đã rẽ vào lánh tạm trong một căn nhà hoang bên con dốc vắng người qua lại. Căn nhà có vẻ khi xưa là một tiệm tạp hóa với biển hiệu cũ kỹ bám đầy bồ hóng, khiến người ta khó lòng đọc được trên đó viết gì. Định bụng nghỉ tạm một đêm rồi sáng hôm sau chuồn sớm, cả ba không ngờ chờ đợi cả bọn sẽ là một đêm không ngủ, với bao điều kỳ bí bắt đầu từ một phong thư bất ngờ gửi đến…\nTài kể chuyện hơn người đã giúp Keigo khéo léo thay đổi các mốc dấu thời gian và không gian, chắp nối những câu chuyện tưởng chừng hoàn toàn riêng rẽ thành một kết cấu chặt chẽ, gây bất ngờ từ đầu tới cuối."));
        list1.add(new BookList(5, "Văn học", "Lưu Từ Hân",	"NXB Hà Nội","Tam thể 1 - Tái bản", "item_5", 6, 5, 90000, "Uông Diểu, vị giáo sư về vật liệu nano ngày nào cũng đăng nhập vào “Tam Thể”. Tại trò chơi online đó, anh đắm chìm trong một thế giới khác, nơi một nền văn minh có thể chỉ kéo dài vài ngày, bầu trời có thể xuất hiện ba mặt trời cùng lúc và con người còn phải biến thành xác khô để sinh tồn.\nNhưng anh không thể ngờ, thế giới khắc nghiệt trong Tam Thể là có thực, chỉ cách trái đất chừng bốn năm ánh sáng, và trò chơi ảo kia lại là một cánh cửa để những sinh vật của thế giới ấy bước đến xâm chiếm địa cầu này. Kinh hoàng, Uông Diểu tìm mọi cách ngăn chặn điều đó. Nhưng anh, cũng như cả địa cầu, không biết rằng, cánh cửa nọ đã được mở toang, từ mấy chục năm về trước."));
        list1.add(new BookList(6, "Văn học", "Albert Camus","NXB Dân Trí","Dịch hạch - Nobel Văn Chương 1957", "item_6", 2, 3.5, 97000, "Sau hơn 60 năm sau ngày mất của tác giả, thế giới đã chứng kiến những đại dịch bệnh thật sự, như Ebola, COVID-19, Không còn là một dịch bệnh hư cấu, những vấn đề liên quan tới nhân loại, cách loài người đối mặt với dịch bệnh, hàng loạt tầng sâu ý nghĩa trong Dịch hạch bỗng trở nên dễ hiểu, cấp thiết trong thời đại ngày nay."));
        list1.add(new BookList(7, "Văn học","Dương Thụy",	"NXB Hà Nội", "Bồ câu chung mái vòm - Tái bản", "item_7", 9, 4, 79000, "Những truyện ngắn trong tập sách này được tôi viết sau khi đi du học về với nhiều kỷ niệm thân thương. Tôi vẫn thường mơ thấy lại những chú bồ câu đáng yêu dưới mái vòm nhà thờ yên ả. Tôi nhớ hoài những buổi chiều lang thang ở Rennes trong làn gió thu lãng mạn, nơi tôi đã viết truyện ngắn \"Một mùa thu ở Rennes\". Và bạn cũng sẽ bắt gặp những chuyến chu du của tôi đến những miền đất lạ trong \"Con gà nói tiếng Đức\", \"Bất chợt ở La Mã\", \"Tú cầu vùng Bretagne\""));
        list1.add(new BookList(8, "Văn học", "Xu",	"NXB Thế Giới","Hãy là tất cả, hoặc không là gì", "item_8", 8, 4.5, 126000, "Nếu tuổi trẻ của bạn là vô vàn thương tích, nhưng bạn vẫn chẳng biết cách để trở nên cứng cỏi, can trường. Nếu bạn luôn than trách cuộc đời bất công, trong khi bản thân chỉ nghĩ về những ước mơ chứ chưa từng nỗ lực. Bạn đi qua những hoang mang, ngờ vực như thế bằng sự non nớt, không dám vượt thoát khỏi vùng an toàn, không dám nghĩ đến ngày mai. Nhưng bạn biết không: Tuổi trẻ đáng tiếc nhất không phải thử rồi sai. Tuổi trẻ đáng tiếc là phải nói hai chữ \"giá như\" vì chưa từng dám thử."));
        list1.add(new BookList(9, "Văn học", "Nguyễn Ngọc Tư",	"NXB Thanh Niên","Yêu Người Ngóng Núi", "item_9", 7, 4.5, 141000, "32 bài tản văn trong “Yêu người ngóng núi” là những câu chuyện “rất tình” về Đất, về Người Nam Bộ. Từ những chi tiết nhỏ như… cục kẹo, đến những vấn đề mang tính sống còn của người nông dân đã được đề cập một cách thấu đáo, chân thành và ý nhị. Có cả những chuyện tưởng chừng riêng tư nhưng lại hòa vào dòng thời sự chung như chuyện đi du lịch, nuôi dạy con, và cả chuyện yêu đương…"));
        list1.add(new BookList(10, "Văn học", "Thomas More",	"NXB Hội Nhà Văn", "Utopia - Địa đàng trần gian - Tái bản 2020", "item_10", 5, 5, 36000, "Thomas More đã viết Utopia trong những năm từ 1515 đến 1516, thời điểm trí tưởng tượng của con người bị khuyấy đảo trước sự mở rộng đột ngột của các quan niệm về thế giới, và miêu tả của Amerigo Vespucci về những cuộc du hành về phía Tây của ông được in lần đầu tiên vào năm 1507, vẫn còn mới nguyên trong trí óc các học giả uyên thâm. More đã tưởng tượng ra một Raphael Hythloday-cái tên phiên từ tiếng Hy Lạp sang có nghĩa là “Thạo những chuyện tầm phào” – một lữ khách đồng hành cùng Vespucci nhưng không trở về sau chuyến du hành thứ ba, mà rời bỏ các bạn hữu để một thân một mình lang thang nơi xứ sở xa lạ. Rồi anh ta đã tìm thấy hòn đảo Utopia-gốc Hy Lạp sáng có nghĩa là “Không ở nơi đâu”. Và đó là một địa đàng lý tưởng, được More vẽ nên với một phong cách châm biếm sâu cay lối lãnh đạo quốc sự kiểu Châu Âu, mà cụ thể là kiểu Anh."));
    }
    public void addToList2() {
        list2.add(new BookList(11, "Kinh tế", "Harvard Business Review Press",	"Harvard Business Review Press","Crypto: The Insights You Need From Harvard Business Review - HBR Insights Series", "item_11", 5, 5, 430350, "The crypto era has arrived, and business will never be the same. Real applications of crypto technology are growing exponentially: cryptocurrency payments are moving frictionlessly across borders; NFTs are generating real value for creators and consumers alike; and new blockchain-enabled business models are being built around decentralized finance and Web3. What do you and your company need to know and do today to create new opportunities and avoid disruption? Crypto: The Insights You Need from Harvard Business Review will show you how innovative organizations of all kinds are embracing decentralized technology, reinventing themselves, and thriving in the new age of crypto. Business is changing. Will you adapt or be left behind? Get up to speed and deepen your understanding of the topics that are shaping your company's future with the Insights You Need from Harvard Business Review series. Featuring HBR's smartest thinking on fast-moving issues—blockchain, cybersecurity, AI, and more—each book provides the foundational introduction and practical case studies your organization needs to compete today and collects the best research, interviews, and analysis to get it ready for tomorrow. You can't afford to ignore how these issues will transform the landscape of business and society. The Insights You Need series will help you grasp these critical ideas—and prepare you and your company for the future."));
        list2.add(new BookList(12, "Kinh tế", "Philip Kotler","Wiley",	"Marketing 6.0: The Future Is Immersive", "item_12", 3, 4, 566100, "Rediscover the fundamentals of marketing along with the rise of metamarketing from the best in the business In Marketing 6.0, the celebrated promoter of the “Four P’s of Marketing,” Philip Kotler, explains how marketers can use technology to address customers’ needs and make a difference in the world. In a new age of metamarketing, this book provides marketers with a way to integrate technological and business model evolution with the dramatic shifts in consumer behavior that have happened in the last decade. Readers will learn about: The building blocks of metamarketing Generation Z and Generation Alpha and the technologies they use daily How to tap into metaverses and extended reality The potential obstacles and solutions for creating a more interactive and immersive experience. Marketing has evolved to address global challenges and changing customer expectations. Incorporating sustainability themes and new technologies for customer engagement are essential for businesses to remain relevant. Indeed, marketing has shifted from traditional to digital, but most customers still value some forms of human interaction. As a result, multichannel and omnichannel marketing have become popular among marketers aiming to leverage both traditional and digital engagement. Metamarketing goes beyond that and offers a genuine physical and digital convergence by providing a more interactive and immersive customer experience across physical and digital spaces."));
        list2.add(new BookList(13, "Kinh tế", "Nick Timiraos	Little", "Brown & Company","Trillion Dollar Triage: How Jay Powell And The Fed Battled A President And A Pandemic - And Prevented Economic Disaster", "item_13", 4, 4, 547200, "The inside story, told with “insight, perspective, and stellar reporting,” of how an unassuming civil servant created trillions of dollars from thin air, combatted a public health crisis, and saved the American economy from a second Great Depression (Alan S. Blinder, former Vice Chair of the Federal Reserve). By February 2020, the U.S. economic expansion had become the longest on record. Unemployment was plumbing half-century lows. Stock markets soared to new highs. One month later, the public health battle against a deadly virus had pushed the economy into the equivalent of a medically induced coma. America’s workplaces—offices, shops, malls, and factories—shuttered. Many of the nation’s largest employers and tens of thousands of small businesses faced ruin. Over 22 million American jobs were lost. The extreme uncertainty led to some of the largest daily drops ever in the stock market. Nick Timiraos, the Wall Street Journal’s chief economics correspondent, draws on extensive interviews to detail the tense meetings, late night phone calls, and crucial video conferences behind the largest, swiftest U.S. economic policy response since World War II. Trillion Dollar Triage goes inside the Federal Reserve, one of the country’s most important and least understood institutions, to chronicle how its plainspoken chairman, Jay Powell, unleashed an unprecedented monetary barrage to keep the economy on life support. With the bleeding stemmed, the Fed faced a new challenge: How to nurture a recovery without unleashing an inflation-fueling, bubble-blowing money bomb? Trillion Dollar Triage is the definitive, gripping history of a creative and unprecedented battle to shield the American economy from the twin threats of a public health disaster and economic crisis. Economic theory and policy will never be the same."));
        list2.add(new BookList(14, "Kinh tế", "Carol Loomis","Penguin"	, "Tap Dancing to Work: Warren Buffett on Practically Everything, 1966-2013", "item_14", 6, 4, 259350, "Tap Dancing to Work compiles six decades of writing on legendary investor Warren Buffett, from Carol Loomis, the reporter who knows him best. Warren Buffett built Berkshire Hathaway into something remarkable - and Fortune had a front-row seat When Fortune writer Carole Loomis first mentioned a little-known Omaha hedge fund manager in a 1966 article, she didn't dream that Warren Buffett would become the world's greatest investor. Nor did she imagine that she and Buffett would be close friends. As Buffett's fortune and reputation grew, Loomis used her unique insight into his thinking to chronicle his work, writing scores of stories that tracked his many accomplishments - and his occasional mistakes. Now Loomis has collected and updated the best Buffett articles from Fortune between 1966 and 2013, including cover stories and pieces by Buffett himself. Readers will gain fresh insights into Buffett's investment strategies and his thinking on management, philanthropy, public policy, and even parenting. Scores of Buffett books have been written, but none can claim this combination of trust, deep understanding of Buffett's world, and a long-term perspective. 'The clearest picture of life according to the world's fourth-richest man' Evening Standard 'Stuffed with nuggets and insights - a Christmas fruitcake for the investor' Financial Times Carol J. Loomis is a senior editor-at-large at Fortune, where she has worked since 1954. She has been the magazine's expert on Warren Buffett since 1966 and has edited his annual letter to shareholders since 1977. Her many honours include five lifetime achievement awards, including a Gerald Loeb Award for business journalism and Time Inc.'s first-ever Henry Luce Award. This is her first book. She lives in Westchester County."));
        list2.add(new BookList(15, "Kinh tế", "Alice Schroeder","Bloomsbury Publishing PLC"	,"The Snowball: Warren Buffett and the Business of Life", "item_15", 7, 4.5, 320150, "Here is THE book recounting the life and times of one of the most respected men in the world, Warren Buffett. The legendary Omaha investor has never written a memoir, but now he has allowed one writer, Alice Schroeder, unprecedented access to explore directly with him and with those closest to him his work, opinions, struggles, triumphs, follies, and wisdom. The result is the personally revealing and complete biography of the man known everywhere as “The Oracle of Omaha.” Although the media track him constantly, Buffett himself has never told his full life story. His reality is private, especially by celebrity standards. Indeed, while the homespun persona that the public sees is true as far as it goes, it goes only so far. Warren Buffett is an array of paradoxes. He set out to prove that nice guys can finish first. Over the years he treated his investors as partners, acted as their steward, and championed honesty as an investor, CEO, board member, essayist, and speaker. At the same time he became the world’s richest man, all from the modest Omaha headquarters of his company Berkshire Hathaway. None of this fits the term “simple.” When Alice Schroeder met Warren Buffett she was an insurance industry analyst and a gifted writer known for her keen perception and business acumen. Her writings on finance impressed him, and as she came to know him she realized that while much had been written on the subject of his investing style, no one had moved beyond that to explore his larger philosophy, which is bound up in a complex personality and the details of his life. Out of this came his decision to cooperate with her on the book about himself that he would never write. Never before has Buffett spent countless hours responding to a writer’s questions, talking, giving complete access to his wife, children, friends, and business associates—opening his files, recalling his childhood. It was an act of courage, as The Snowball makes immensely clear. Being human, his own life, like most lives, has been a mix of strengths and frailties. Yet notable though his wealth may be, Buffett’s legacy will not be his ranking on the scorecard of wealth; it will be his principles and ideas that have enriched people’s lives. This book tells you why Warren Buffett is the most fascinating American success story of our time."));
        list2.add(new BookList(16, "Kinh tế", "Reeves Wiedeman",	"Back Bay Books","Billion Dollar Loser: The Epic Rise And Spectacular Fall Of Adam Neumann And WeWork", "item_16", 9, 5, 328700, "A Wall Street Journal Business Bestseller: This \"vivid\" inside story of WeWork and its CEO tells the remarkable saga of one of the most audacious, and improbable, rises and falls in American business history (Ken Auletta).\n\nChristened a potential savior of Silicon Valley's startup culture, Adam Neumann was set to take WeWork, his office share company disrupting the commercial real estate market, public, cash out on the company's forty-seven billion dollar valuation, and break the string of major startups unable to deliver to shareholders. But as employees knew, and investors soon found out, WeWork's capital was built on promises that the company was more than a real estate purveyor, that in fact it was a transformational technology company.\n\nVeteran journalist Reeves Weideman dives deep into WeWork and it CEO's astronomical rise, from the marijuana and tequila-filled board rooms to cult-like company summer camps and consciousness-raising with Anthony Kiedis. Billion Dollar Loser is a character-driven business narrative that captures, through the fascinating psyche of a billionaire founder and his wife and co-founder, the slippery state of global capitalism.\n\nA Wall Street Journal Business Bestseller\n\n\"Vivid, carefully reported drama that readers will gulp down as if it were a fast-paced novel\" (Ken Auletta)"));
        list2.add(new BookList(17, "Kinh tế", "Benjamin Graham","HarperBusiness","The Intelligent Investor: The Definitive Book on Value Investing. A Book of Practical Counsel - Revised Edition - Collins Business Essentials", "item_17", 1, 4.5, 371700, "This classic text is annotated to update Graham's timeless wisdom for today's market conditions...\n\nThe greatest investment advisor of the twentieth century, Benjamin Graham, taught and inspired people worldwide. Graham's philosophy of \"value investing\" -- which shields investors from substantial error and teaches them to develop long-term strategies -- has made The Intelligent Investor the stock market bible ever since its original publication in 1949.\n\nOver the years, market developments have proven the wisdom of Graham's strategies. While preserving the integrity of Graham's original text, this revised edition includes updated commentary by noted financial journalist Jason Zweig, whose perspective incorporates the realities of today's market, draws parallels between Graham's examples and today's financial headlines, and gives readers a more thorough understanding of how to apply Graham's principles.\n\nVital and indispensable, this HarperBusiness Essentials edition of The Intelligent Investor is the most important book you will ever read on how to reach your financial goals."));
        list2.add(new BookList(18, "Kinh tế", "Warner Books Inc",	"Tom Hopkins","Selling in Tough Times: Secrets to Selling When No One Is Buying", "item_18", 10, 5, 309700, "Tough Times can be brought on by any number of factors: a down economy, Mother Nature, shifts in customers' needs, national tragedy--the list goes on and on. These types of changes can be extremely disruptive, even paralyzing, when we're not prepared for them. While many see no other option than to \"sit tight\" and \"ride things out\" when crisis strikes, true career professionals in selling understand that the only way to deal with adversity is to meet it head-on. That's why a positive attitude and a proactive approach to problem-solving are two of the most essential ingredients for success in selling--and why those who embrace them not only to survive but thrive, even in the most difficult of circumstances. Now, in his latest book, SELLING IN TOUGH TIMES, world-renowned selling expert Tom Hopkins puts his real-world, in-the-trenches experience to work and shares his plan to reverse the momentum of tough times--and even capitalize on them. With exercises to help you discover previously overlooked opportunities and eliminate waste, along with out-of-the-box methods for recruiting new customers and key tips on how to solidify your existing business, Hopkins gives you powerful ways to spur sales now and for years to come. Learn how to: Mine your client list to generate new leads Keep--and reward--your current customers so that they're loyal for life. Reduce the sales resistance that plagues tough times with tactics that overcome consumers' fears. Woo clients from your competition with 12 new strategies specially tailored for tough times.\n\nCycles will come and go, but the principles of great selling and those who live by them stand firm. Find out how you can achieve your maximum selling potential, whatever the business climate, in SELLING IN TOUGH TIMES today."));
        list2.add(new BookList(19, "Kinh tế", "Gallup Press",	"Tom Rath","StrengthsFinder 2.0","item_19", 5, 5, 599450, "Do you have the opportunity to do what you do best every day?\n\nChances are, you don't. All too often, our natural talents go untapped. From the cradle to the cubicle, we devote more time to fixing our shortcomings than to developing our strengths.\n\nTo help people uncover their talents, Gallup introduced the first version of its online assessment, StrengthsFinder, in 2001 which ignited a global conversation and helped millions to discover their top five talents.\n\nIn its latest national bestseller, StrengthsFinder 2.0, Gallup unveils the new and improved version of its popular assessment, language of 34 themes, and much more (see below for details). While you can read this book in one sitting, you'll use it as a reference for decades.\n\nLoaded with hundreds of strategies for applying your strengths, this new book and accompanying website will change the way you look at yourself--and the world around you--forever.\n\nAvailable exclusively in StrengthsFinder 2.0:\n(using the unique access code included with each book)\n\n* A new and upgraded edition of the StrengthsFinder assessment\n* A personalized Strengths Discovery and Action-Planning Guide for applying your strengths in the next week, month, and year\n* A more customized version of your top five theme report\n* 50 Ideas for Action (10 strategies for building on each of your top five themes)"));
        list2.add(new BookList(20, "Kinh tế", "Jeremy Kourdi","Nicholas Brealey","100 Business Tools For Success", "item_20", 3, 4.5, 289800, "The world is full of business ideas.But how do you know which the best ones are?\n\nAnd how do you find time to read them?\n\n100 Business Tools for Success may be a little book, but it contains the very best business tools that have come from the very best business brains on the planet. Each is summarized over just two pages, so that you can quickly gain access to the insights which are driving the most successful people in all walks of life.\n\nA must for all business professionals..."));
    }
    public void addToList3() {
        list3.add(new BookList(21, "Tâm lý - Kỹ Năng","Ngô Hiểu Huy","NXB Phụ Nữ Việt Nam"	,"Phương Pháp Giáo Dục Montessori - Phương Pháp Giáo Dục Tối Ưu Dành Cho Trẻ 0-6 Tuổi", "item_21", 10, 4, 62500, "Hiện nay, phương pháp giáo dục Montessori đang rất thịnh hành, những lớp hướng dẫn, khóa tập huấn ngắn hạn về phương pháp này liên tục được mở ra khắp nơi, hàng loạt trường mầm non rầm rộ mở \"\"lớp Montessori\"\". Tuy nhiên giáo dục Montessori là thế nào? Rất ít người thực sự hiểu về nó, do đó có không ít những trường hợp hiểu lầm hiểu sai xuất hiện. Để giúp các bậc phụ huynh và giáo viên mầm non hiểu đúng về giáo dục Montessori và ứng dụng nó hiệu quả vào dạy trẻ, cuốn sách này sẽ giới thiệu đầy đủ quá trình phát triển và hoạt động thích hợp trên các phương diện tình cảm, giao tiếp, vận động và trí tuệ của trẻ em từ 0 - 6 tuổi. Qua đó, giúp cha mẹ hiểu được quy luật phát triển tự nhiên của con trẻ, tạo ra môi trường Montessori phong phú và sống động ngay tại nhà, sử dụng các vật liệu thường ngày để chế tạo ra các giáo cụ Montessori đơn giản, giúp trẻ có một sự khởi đầu tốt đẹp."
        ));
        list3.add(new BookList(22, "Tâm lý - Kỹ Năng", "Barbara J Patterson",	"NXB Trẻ","Bên Kia Cầu Vồng - Nuôi Dưỡng Đứa Trẻ Từ Sơ Sinh Đến Bảy Tuổi - Beyond The Rainbow Bridge", "item_22", 8, 3.5, 90000, "Đời sống trong thời công nghệ khiến chúng ta quen với những thứ được làm sẵn từ thức ăn tới quần áo, đồ chơi… Cuốn sách này sẽ là món quà đầy ý nghĩa đối với các thầy cô giáo và các ông bố bà mẹ có con từ sơ sinh đến bảy tuổi. Những bài giảng dành cho phụ huynh của cô Barbara Patterson, một giáo viên mầm non Waldorf tại Chicago, Mỹ, đã được một người dự, bà Pamela Bradley, ghi chép lại. Toàn bộ sách được viết theo lối kể chuyện cực kỳ giản dị, nhưng đầy đủ những gì mà cha mẹ hay giáo viên, những người làm công tác giáo dục trẻ ở lứa tuổi bảy năm đầu đời cần được trang bị: ví dụ phần chia sẻ về tầm quan trọng của sức ấm đối với trẻ sơ sinh và trẻ mầm non. Độc giả được nhắc về những chi tiết nhỏ, trước nay ít người để ý nhưng vô cùng quan trọng đối với trẻ ở trường cũng như ở nhà."
        ));
        list3.add(new BookList(23, "Tâm lý - Kỹ Năng", "Tiêu Vệ, Lê Tâm",	"NXB Kim Đồng","Học Cho Ai? Học Để Làm Gì? - Tập 1", "item_23", 5, 4, 36000, "Chúng ta không thể lựa chọn được xuất thân của mình, nhưng bằng sự cần cù chăm chỉ, cùng với nghị lực kiên định và tinh thần không khuất phục trước khó khăn, chúng ta có thể lựa chọn việc không ngừng học tập, dùng tri thức và năng lực để thay đổi cuộc đời. Cuộc sống phía trước đầy thử thách và cơ hội. Một người nếu có tài năng và sở trường mà xã hội cần đến thì dù anh ta ở bất kì hoàn cảnh nào trong xã hội, sớm muộn sẽ có một ngày được thi thố tài năng, giúp ích cho đời."
        ));
        list3.add(new BookList(24, "Tâm lý - Kỹ Năng", "Tiêu Vệ, Lê Tâm",	"NXB Kim Đồng","Học Cho Ai? Học Để Làm Gì? - Tập 2", "item_24", 5, 4, 36000, "Chúng ta không thể lựa chọn được xuất thân của mình, nhưng bằng sự cần cù chăm chỉ, cùng với nghị lực kiên định và tinh thần không khuất phục trước khó khăn, chúng ta có thể lựa chọn việc không ngừng học tập, dùng tri thức và năng lực để thay đổi cuộc đời. Cuộc sống phía trước đầy thử thách và cơ hội. Một người nếu có tài năng và sở trường mà xã hội cần đến thì dù anh ta ở bất kì hoàn cảnh nào trong xã hội, sớm muộn sẽ có một ngày được thi thố tài năng, giúp ích cho đời."
        ));
        list3.add(new BookList(25,"Tâm lý - Kỹ Năng", "Đặng Hoàng Giang",	"NXB Hội Nhà Văn","Đại Dương Đen - Những Câu Chuyện Từ Thế Giới Của Trầm Cảm", "item_25", 10, 4.5, 180000, "“Tôi sợ những cơn của mình, chúng xâm chiếm não bộ tôi, nhấn chìm lý trí của tôi, chúng phơi bày sự đau đớn, cô đơn, nỗi sầu thảm suốt những năm tháng niên thiếu của tôi, sự ám ảnh của bạo lực, của lẻ loi, của tức giận vì chẳng được ai giúp đỡ. Trong những giấc mơ, tôi thét lên với mọi người, cố gắng diễn đạt sự sợ hãi và tuyệt vọng của mình, nhưng không ai hiểu.”\n\nĐại dương đen là hành trình nhẫn nại của tác giả Đặng Hoàng Giang cùng người trầm cảm, kể cho chúng ta câu chuyện vừa dữ dội vừa tê tái về những số phận, mà vì định kiến và sự thiếu hiểu biết của chính gia đình và xã hội, đã bị tước đi quyền được sống với nhân phẩm, được cống hiến, được yêu thương và hạnh phúc.\n\nLà tiếng nói chia sẻ hiếm hoi với thế giới của người trầm cảm, là lời kêu gọi xóa bỏ định kiến xã hội, Đại dương đen đồng thời là công trình giáo dục tâm lý, cung cấp kiến thức căn bản về trầm cảm, hình hài nó thế nào, nó từ đâu tới, nó có thể phá hủy ra sao, có những phương thức trị liệu nào, và mỗi chúng ta có thể làm gì để những người không may mắn được sống an hòa với nhân phẩm của mình.\n\nTÁC GIẢ:\n\nTiến sĩ Đặng Hoàng Giang là chuyên gia phát triển, nhà hoạt động xã hội và tác giả chính luận. Các hoạt động nghiên cứu và vận động chính sách của anh nhằm nâng cao chất lượng quản trị quốc gia và thúc đẩy tiếng nói của người dân. Anh nỗ lực mở rộng không gian xã hội dân sự, truyền bá tri thức, phá bỏ định kiến và kỳ thị, góp phần xây dựng một xã hội khoan dung và trắc ẩn.\n\nĐặng Hoàng Giang tốt nghiệp kỹ sư tin học tại Đại học Công nghệ Ilmenau, Đức, và có bằng tiến sĩ kinh tế phát triển của Đại học Công nghệ Vienna, Áo. Những cuốn sách và bài viết của anh có sức ảnh hưởng rộng rãi trong xã hội."));
        list3.add(new BookList(26,"Tâm lý - Kỹ Năng", "Prentice Mulford","NXB Thanh Niên"	,"Luật Hấp Dẫn - Quy Luật Về Sức Mạnh Tâm Trí VÀ Phát Huy Năng Lượng Tích Cực Để Làm Chủ Định Mệnh", "item_26", 4, 3, 100000, "Cách chúng ta suy nghĩ tạo nên thực tế cuộc sống của chúng ta. Nuôi dưỡng tâm trí và linh hồn chính là nuôi dưỡng con người chân thực của mình, để chữa lành bản thân từ bên trong và khơi gợi những sức mạnh tiềm ẩn.\n\nCuốn sách này xoay quanh Luật Hấp dẫn, diễn giải những quy luật tâm thức đã luôn tồn tại và không ngừng hiện hữu trong mọi khía cạnh hoạt động của con người, giúp chúng ta thấu hiểu và bắt đầu học cách sử dụng nguồn năng lượng khổng lồ đang ẩn sâu trong cơ thể, để can đảm tiến bước trên hành trình dựng xây sự nghiệp và cuộc đời viên mãn mà ta mong muốn.\n\nThông tin tác giả:\n\nPrentice Mulford (1834-1891) là cây viết Mỹ đóng vai trò quan trọng trong quá trình phát triển của phong trào Tư tưởng Mới. Nhiều nguyên tắc và quy luật tiêu biểu của phong trào đã được Mulford trình bày chi tiết trong những bài luận xuất bản vào năm tháng cuối đời. Ông dành nhiều tâm sức để viết về Luật Hấp dẫn - quy luật đã truyền cảm hứng và đặt nền tảng cho nguyên tắc tư duy tích cực, đồng thời là một quy luật vẫn đang không ngừng được ứng dụng trong xã hội hiện đại."));
        list3.add(new BookList(27,"Tâm lý - Kỹ Năng", "Adam Grant"	,"NXB Dân Trí","Dám Nghĩ Lại", "item_27", 6, 3.5, 116000, "Mười hai trong số mười lăm thành viên đội cứu hỏa đã tử nạn trong đám cháy gần đỉnh Mann Gulch vào năm 1949. Hai trong số ba người sống sót là nhờ có thể lực tốt nên kịp chạy thoát khỏi đám cháy; người còn lại, Wagner Dodge, đã thoát khỏi lưỡi hái tử thần bằng tư duy linh hoạt của mình. Những đồng đội của Wagner Dodge mất mạng vì đã hành động theo những kỹ năng và hiểu biết đã ăn sâu trong tiềm thức của họ. Dodge thì khác, anh không tìm cách dập lửa theo kiến thức tích lũy được, mà nhanh chóng nhận định tình hình và tạo ra một lối thoát hiểm bằng cách đốt trụi đám cỏ trước mặt, chặn đứng nguồn bắt lửa của đám cháy phía sau. Tưởng rằng đó là hành động điên rồ, nhưng Dodge đã thoát chết nhờ kịp thời tái tư duy. \nTáitưduy, theo Adam Grant, là suy nghĩ lại, cân nhắc lại quan điểm, định kiến, thậm chí là kiến thức của bản thân, cũng có thể là suy nghĩ thoát khỏi lối mòn tư duy. Cũng theo ông, để chinh phục kỹ năng này, bạn cần quên đi những gì đã học, đồng thời thiết lập và duy trì vònglặptáitưduy. \nTrong một thế giới đầy biến động, lối tư duy cố hữu và hiểu biết ăn sâu của chúng ta có thể trở thành lời nguyền án ngữ mọi sự tiến bộ của chính mình. Hơn thế nữa,bạn dễ đi thụt lùi vì “thiếu năng lực siêu nhận thức, tức là khả năng tư duy về cách tư duy của chính mình”, tác giả Adam nhận định. “Dám nghĩ lại” (Think Again) là cuốn sách sẽ hướng dẫn chúng ta từ bỏ việc bám chấp vào những hiểu biết của bản thân để tư duy cởi mở và linh hoạt hơn."));
        list3.add(new BookList(28,"Tâm lý - Kỹ Năng", "James Clear","NXB Thế Giới","Atomic Habits - Thay Đổi Tí Hon Hiệu Quả Bất Ngờ", "item_28", 9, 4.5, 135000, "Một thay đổi tí hon có thể biến đổi cuộc đời bạn không?\n\nHẳn là khó đồng ý với điều đó. Nhưng nếu bạn thay đổi thêm một chút? Một chút nữa? Rồi thêm một chút nữa? Đến một lúc nào đó, bạn phải công nhận rằng cuộc sống của mình đã chuyển biến nhờ vào một thay đổi nhỏ…\n\nVà đó chính là sức mạnh của thói quen nguyên tử.\n\nTác giả:\n\nJames Clear là tác giả người Mỹ, nhiếp ảnh gia, nhà khởi nghiệp, và là người sáng tạo The Habits Academy.\n\nAnh nghiên cứu về những thói quen, việc đưa ra quyết định và sự cải tiến liên tục. Trang jamesclear.com có hàng triệu lượt truy cập mỗi tháng.\n\nBài viết của James Clear được đăng trên New York Times, Entrepreneur, Time… Anh cũng là diễn giả thường xuyên tại các công ty nằm trong bảng xếp hạng Fortune 500."));
        list3.add(new BookList(29,"Tâm lý - Kỹ Năng", "Liu Yong",	"NXB Kim Đồng","Kĩ Năng Vàng Cho Học Sinh Trung Học - Học Cách Quản Lí Cuộc Sống", "item_29", 5, 4, 36000, "Thế giới rộng lớn có biết bao điều phức tạp khiến những bạn trẻ mới chập chững bước vào đời phải bối rối, băn khoăn. Cuốn sách Học Cách Quản Lí Cuộc Sống của Liu Yong mang đến những câu chuyện, bài học giản dị, gần gũi, giúp các bạn trẻ phần nào đó thấu hiểu thế nào là tình yêu, biết cách ứng xử với thất bại, thành công, cùng những tình huống khó khăn khác trong cuộc sống.\n\nĐÔI ĐIỀU VỀ TÁC GIẢ\n\nLiu Yong vừa là họa sĩ, nhà văn, vừa là diễn giả nổi tiếng, một người sống tích cực, chăm chỉ, luôn muốn vượt qua chính mình. Ông từng là họa sĩ tại Viện Bảo tàng Mĩ thuật Danville, họa sĩ chuyên trách tại Trường Đại học St.John’s, là Phó giáo sư Trường Saint Vincent tại Mĩ, là Giáo sư thỉnh giảng của Trường Đại học Hạ Môn, Trung Quốc.\n\nCho đến nay Liu Yong đã xuất bản hơn một trăm đầu sách bằng tiếng Anh và tiếng Trung, những bài luận về cuộc sống cùng những bài tản văn truyền cảm hứng ấm áp của ông đã trở thành những cuốn sách bán chạy nhất. Ông cũng được mệnh danh là “nhà văn gắn kết tâm hồn với lứa tuổi thanh thiếu niên”. Ông đã từng quyên góp xây dựng 40 trường tiểu học và ông cũng là một trong số ít họa sĩ đương đại thường xuyên có tranh được vào Nhà đấu giá Quốc tế Sotheby’s.\n\n“Đọc thêm nhiều sách hơn, đi nhiều nơi hơn, suy nghĩ nhiều hơn, sáng tạo nhiều hơn, thì bạn càng dễ dàng đến với thành công.” - Liu Yong"));
        list3.add(new BookList(30,"Tâm lý - Kỹ Năng", "Liu Yong",	"NXB Kim Đồng","Kĩ Năng Vàng Cho Học Sinh Trung Học - Học Kĩ Năng Để Thành Công", "item_30", 4, 4, 36000, "Thành công không phải chuyện một sớm một chiều. Sai lầm, vấp ngã, đau khổ… luôn rình rập trên con đường hướng tới thành công. Chúng ta phải biết cách chiến thắng và vượt qua chúng, bởi đó là quá trình tất yếu phải trải qua, không thì niềm vui của thành công chẳng phải sẽ vơi đi rất nhiều sao?\n\nKĩ năng vàng cho học sinh trung học là bộ sách 6 cuốn dành cho học sinh từ 10 – 16 tuổi. Bộ sách này gồm những chủ đề rất phù hợp cho các em đọc tham khảo, gồm nhiều phương diện, giúp các em hiểu được những vấn đề có thể sẽ gặp phải trong quá trình trưởng thành.\n\nTác giả sử dụng lối viết thân mật, hài hước với những câu chuyện phong phú và cô đọng, tái hiện các tình huống cuộc sống qua từng chi tiết, từng khung cảnh để gợi ý, hướng dẫn cho những người trẻ cách tư duy, cách ứng xử và giải quyết những vướng mắc, khơi nguồn cảm hứng, mở ra tầm nhìn rộng lớn và từng bước trưởng thành."));
    }
    public void addToList4() {
        list4.add(new BookList(31, "Sách thiếu nhi","Daniel Howarth",	"NXB Kim Đồng", "Những Lời Yêu Thương Của Con Trẻ Dành Tặng Các Mẹ - Vì Sao Tớ Yêu Mẹ - Tái Bản 2019", "item_31", 10, 5, 18750, "Seri sách tranh “Vì sao tớ yêu...” được đông đảo bạn đọc nhỏ tuổi yêu thích và say mê đọc mỗi tối. Bằng tranh vẽ biểu cảm, thơ mộng về thế giới tự nhiên cùng lời văn êm ái, ngọt ngào như thơ, bộ sách “Vì sao tớ yêu...” là những lời yêu thương của con trẻ trên khắp thế giới dành tặng cho ông bà, cha mẹ mình..."));
        list4.add(new BookList(32, "Sách thiếu nhi", "Hiếu Minh, Phùng Nguyên Quang",	"NXB Kim Đồng","Cổ Tích Việt Nam Cho Bé - Con Cóc Là Cậu Ông Giời - Tái Bản 2022", "item_32", 5, 4, 18000, "Một năm, trời hạn hán, muôn loài dưới mặt đất sắp chết khô cả, Cóc dẫn đầu một đoàn các con vật lên kiện Trời. Trên thiên đình, điều gì xảy ra nhỉ? Chúng mình cùng đọc truyện nhé! Bộ “Cổ tích Việt Nam cho bé” tuyển chọn những câu chuyện nổi tiếng trong kho tàng truyện cổ tích Việt Nam, được kể lại ngắn gọn, súc tích cùng với hình minh họa đẹp phù hợp với lứa tuổi mẫu giáo, tiểu học."));
        list4.add(new BookList(33, "Sách thiếu nhi", "Yosbook, Xiao Li",	"NXB Kim Đồng","Mẹ Hỏi Bé Trả Lời 4-5 Tuổi - Tái Bản 2019", "item_33", 6, 4, 22500, "Bộ sách nhỏ xinh “Mẹ hỏi bé trả lời” tập hợp những trò chơi phong phú, câu đố thông minh giúp bé và cha mẹ có thể “học mà chơi, chơi mà học” qua các chủ đề: cách ứng xử, câu hỏi về tự nhiên, không gian, phân biệt hình khối, ngôn ngữ, toán học... Sách phân chia theo từng lứa tuổi với nội dung phong phú, những câu hỏi đáp hàm súc trí tuệ, hình ảnh dễ thương cùng nhiều phương pháp rèn luyện, hoàn toàn có thể khơi dậy trí thông minh và khả năng tiềm ẩn của bé."));
        list4.add(new BookList(34, "Sách thiếu nhi", "Tôn Nguyên Vĩ",
                "NXB Thanh Niên","10 Vạn Câu Hỏi Vì Sao - Khám Phá Cơ Thể Người - Tái Bản 2018", "item_34", 9, 4.5, 38500, "Tuổi thơ là khoảng thời gian đẹp nhất trong cuộc đời mỗi người. Đứng trước thế giới với bao điều kỳ diệu, mang trong mình sự tò mò, khát vọng tìm hiểu, câu nói thường thấy nhất ở trẻ là “Vì sao?”. Để có thể trả lời chính xác câu hỏi của trẻ, không phải là việc đơn giản. Các nghiên cứu cho thấy, sự phát triển của bộ não trẻ diễn ra nhanh nhất vào tuổi 13 trở về trước, là một phụ huynh, khi không mang lại cho trẻ cơ hội suy nghĩ, tìm hiểu, có thể bạn sẽ phải rất ân hận! Xuất phát từ những suy nghĩ trên, bộ sách \"Mười vạn câu hỏi vì sao\" mang lại những câu trả lời cho các em theo từng chủ đề mà các em ham tìm hiểu như: vũ trụ, trái đất, con người, thế giới tự nhiên, xã hội, khoa học… Sách sử dụng ngôn ngữ dễ hiểu, kết hợp những hình ảnh minh họa sinh động, sẽ đem đến cho các em những kiến thức cơ bản, chứa đựng nội dung phong phú, khái quát rộng rãi kiến thức xưa nay, giúp các em vui vẻ, thoải mái, tự tin tiến lên trên con đường thành công tương lai."));
        list4.add(new BookList(35, "Sách thiếu nhi", "Hà Yên","NXB Trẻ"	,"Gieo Mầm Tính Cách - Tự Tin - Tái Bản 2019",	"item_35",9,	4.5,	28800 , "Tính cách của trẻ được hình thành từ rất sớm, thông qua sự giáo dục trong gia đình, qua những việc làm, lời nói, cách ứng xử của những người xung quanh. Nhưng ở độ tuổi nhỏ, không thể ép trẻ phát triển tính cách theo ý muốn của cha mẹ bằng lời dạy dỗ suông, bằng những bài học đạo đức khô khan, mà những tấm gương đẹp về tính cách đó phải được gieo vào trẻ từ từ bằng những câu chuyện sinh động, hấp dẫn. Bộ sách Gieo mầm tính cách (12 tập) là tập hợp những câu chuyện như vậy. Mỗi tập là một hạt giống tính cách gieo vào trẻ những bài học Tử tế, Tha thứ, Kiên trì, Thật thà, Quan tâm, Yêu thương, Mạnh mẽ, Tự tin, Ước mơ, Lịch sự, Hiếu thảo, Công bằng bằng những câu chuyện cảm động, đầy ý nghĩa đáng để suy ngẫm."));
        list4.add(new BookList(36, "Sách thiếu nhi", "Trung Kiên",	"NXB Mỹ Thuật","Kỹ Năng Sống Đầu Đời Cho Bé - Kiến Thức Bảo Vệ Bản Thân", "item_36", 11, 5, 22500, "Kỹ năng sống đầu đời cho bé là một bộ sách vô cùng bổ ích với nội dung được biên soạn công phu, tỉ mỉ, giúp các bạn bồi dưỡng thói quen tốt, biết cách ứng xử khi phải đối mặt với những tình huống khác nhau trong cuộc sống. Bộ sách có những điểm nổi bật như: \n* Bối cảnh được triển khai từ những không gian nhỏ, giúp bé dễ dàng hòa mình vào câu chuyện. \n* Các ví dụ được đưa ra đều cụ thể, chi tiết, phân biệt rõ đúng sai, có tính ứng dụng rất cao trong thực tế. \n* Mỗi tình huống đều đi kèm hình minh họa vui nhộn, khiến các bài học thêm phần hấp dẫn. \nNào, chúng ta hãy cùng mở sách ra và học thêm những kiến thức cực kỳ thú vị nhé!"));
        list4.add(new BookList(37, "Sách thiếu nhi", "Thiện Lộc, Minh Nguyệt",	"NXB Đại Học Quốc Gia TPHCM","Dành Cho Trẻ Mầm Non - Tập Tô Màu Mẫu Giáo - Chủ Đề Động Vật Dưới Nước - 2022", "item_37", 5, 3, 7000, "Bộ sách Bé tập tô màu nhà trẻ với nhiều chủ đề khác nhau, không chỉ rèn khả năng khéo léo, sự linh hoạt của đôi tay mà còn hình thành khả năng nhận biết ở trẻ thông qua việc giới thiệu các sự vật, đối tượng, môi trường sống... \nMỗi trang tô màu gồm có 2 phần: \n- Phần hình mẫu (in màu). \n- Phần hình tô với kích thước lớn, đường nét rõ ràng."));
        list4.add(new BookList(38, "Sách thiếu nhi","Hải Yến",	"NXB Phụ Nữ Việt Nam", "Tập Tô Chữ - Mẫu Giáo 4-5 Tuổi", "item_38", 5, 3.5, 9000, "Trước khi vào lớp 1, các bé mẫu giáo cần được trang bị kiến thức cơ bản về chữ viết giúp các em có được sự tự tin khi tiếp xúc với môi trường học tập ở trường tiểu học. \nCuốn Tập Tô Chữ (Mẫu Giáo 4 - 5 Tuổi) giới thiệu tới các bé những nét chữ cơ bản và bảng chữ cái Tiếng Việt. Sách gồm 23 mẫu chữ cái kèm hướng dẫn viết từng nét chi tiết giúp quý phụ huynh dễ dàng theo dõi và tập viết cho các bé một cách hiệu quả. \nNgoài ra, hình thức vở kẻ ô li giúp các em rèn viết chữ sạch, đẹp, trình bày rõ ràng. Hình minh họa trong từng bài học sẽ giúp việc học trở nên thú vị và giúp các em có hứng thú hơn trong việc học. \nCuốn sách là tài liệu bổ ích giúp các bé tự tin trong môn Chính tả lớp 1."));
        list4.add(new BookList(39, "Sách thiếu nhi", "Hải Yến","NXB Phụ Nữ Việt Nam","365 Truyện Kể Cho Bé Trước Khi Ngủ - Những Câu Truyện Phát Triển IQ Trí Thông Minh", "item_39", 9, 4, 44000, "“365 Truyện kể cho bé trước khi ngủ” tuyển chọn những câu chuyện sống động nhất, thú vị nhất về cuộc sống xung quanh ta, kết hợp hài hòa với những hình vẽ minh họa vui nhộn, dễ thương. Sách là cánh cửa dẫn lối trẻ bước vào giấc mơ với những câu chuyện về thế giới diệu kỳ, nơi chứa đựng những điều ngọt ngào và êm đểm nhất, giúp cho trẻ nhiều năm sau nữa vẫn sẽ mãi nhớ về thế giới tuổi thơ ấy. \nSự kết hợp hoàn hảo giữa nội dung và minh họa, phù hợp với lứa tuổi thuần khiết của trẻ nhỏ, giúp cho trẻ luôn được sống trong những tháng ngày êm đểm hạnh phúc. Đồng thời những kiến thức đan xen về đời sống sẽ giúp trẻ nâng cao trí tuệ của mình. Các bậc phụ huynh thân mến, còn chẩn chừ gì nữa mà không giúp trẻ mở cánh cửa bước vào giấc mơ nhỉ!"));
        list4.add(new BookList(40, "Sách thiếu nhi", "Nhiều Tác Giả",	"NXB Văn Học","Truyện Cổ Tích Thế Giới Hay Nhất - Cô Bé Bán Diêm", "item_40", 7, 4, 14400, "\"\"Cô bé bán diêm\"\" là một truyện ngắn kinh điển của nhà văn Hans Christian Andersen. Câu chuyện xoay quanh cuộc sống khó khăn của một cô bé nghèo đang bán diêm trong đêm Giáng sinh lạnh giá. Được viết bằng ngôn ngữ tình tiết đầy xúc cảm, truyện \"\"Cô bé bán diêm\"\" mang lại thông điệp sâu sắc về lòng nhân ái, hy vọng và sự quý giá của những điều đơn giản trong cuộc sống. \nNằm trong bộ truyện cổ tích thế giới hay nhất, Cuốn sách mang đến cho các em nhỏ những bài học quý giá về cuộc sống. Giúp các em rèn luyện được những đức tính tốt đẹp. \nNội dung truyện ngắn gọn, dễ hiểu, hình vẽ đẹp mắt và in màu nổi bật sẽ làm tăng thêm hứng thú đọc sách cho các bạn thiếu nhi."));

    }
    public void addToList5() {
        list5.add(new BookList(41, "Giáo khoa - Tham khảo", "Nhiều Tác Giả",	"Đại Học Sư Phạm","Toán 3 - Tập 2", "item_41", 2, 4, 19950, "Toán 3 là cuốn sách giáo khoa dành cho học sinh lớp 3 được biên soạn theo Chương trình Giáo dục phổ thông 2018. Sách được biên soạn đáp ứng yêu cầu phát triển phẩm chất và năng lực của học sinh. Các hoạt động học tập được tổ chức theo tiến trình từ dễ đến khó, hướng đến việc khám phá, phát hiện, thực hành, vận dụng giải quyết vấn đề trong thực tiễn, phù hợp với trình độ nhận thức của học sinh. Sách được trình bày hấp dẫn, khơi gợi sự tò mò, kích thích hứng thú, tạo dựng niềm tin trong học tập môn Toán ở học sinh."));
        list5.add(new BookList(42, "Giáo khoa - Tham khảo", "Nhiều Tác Giả","Đại Học Quốc Gia Hà Nội"	,"Atlat Địa lí Việt Nam - Theo Chương Trình Giáo Dục Phổ Thông 2018", "item_42", 3, 4.5, 27900, "Bản đồ là phương tiện giảng dạy và học tập điạ lý không thể thiếu trong nhà trường phổ thông. Cùng với sách giáo khoa, Atlat địa lí Việt Nam là nguồn cung cấp kiến thức, thông tin tổng hợp và hệ thống giúp giáo viên đổi mới phương pháp dạy học, hỗ trợ học. Để đáp ứng nhu cầu bức thiết đó, NXB Giáo dục Việt Nam trân trọng giới thiệu tập Atlat địa lý Việt Nam gồm 21 bản đồ, được in màu rõ nét, liên quan đến các lĩnh vực kinh tế - xã hội. Các bản đồ được xây dựng theo nguồn số liệu của Nhà xuất bản thống kê - Tổng cục thống kê. Đây là tài liệu được phép mang vào phòng thi tốt nghiệp THPT môn Địa lý do Bộ Giáo dục và Đào tạo quy định."));
        list5.add(new BookList(43, "Giáo khoa - Tham khảo", "Chính An Và Nhóm Giáo Viên ĐHSP",	"Thanh Hóa","Bé Chuẩn Bị Vào Lớp 1 - Vở Tập Viết Chữ Cái Viết Thường", "item_43", 2, 4, 8400, "Giúp các bé làm quen với kiến thức cơ bản khi bắt đầu vào lớp 1. Một tài liệu cần thiết cho phụ huynh để có thể rèn luyện con em mình theo một kế hoạch có khoa học."));
        list5.add(new BookList(44, "Giáo khoa - Tham khảo", "Chính An Và Nhóm Giáo Viên ĐHSP",	"Thanh Hóa","Bé Chuẩn Bị Vào Lớp 1 - Vở Tập Viết Chữ Cái Viết Hoa", "item_44", 1, 4, 8400, "Giúp các bé làm quen với kiến thức cơ bản khi bắt đầu vào lớp 1. Một tài liệu cần thiết cho phụ huynh để có thể rèn luyện con em mình theo một kế hoạch có khoa học."));
        list5.add(new BookList(45, "Giáo khoa - Tham khảo", "Nhiều Tác Giả","Đại Học Sư Phạm"	,"Toán 3 - Tập 1", "item_45", 0, 3.5, 23000, "Toán 3 là cuốn sách giáo khoa dành cho học sinh lớp 3 được biên soạn theo Chương trình Giáo dục phổ thông 2018. Sách được biên soạn đáp ứng yêu cầu phát triển phẩm chất và năng lực của học sinh. Các hoạt động học tập được tổ chức theo tiến trình từ dễ đến khó, hướng đến việc khám phá, phát hiện, thực hành, vận dụng giải quyết vấn đề trong thực tiễn, phù hợp với trình độ nhận thức của học sinh. Sách được trình bày hấp dẫn, khơi gợi sự tò mò, kích thích hứng thú, tạo dựng niềm tin trong học tập môn Toán ở học sinh."));
        list5.add(new BookList(46, "Giáo khoa - Tham khảo", "	Mai Lan Hương, Hà Thanh Uyên",	"Đà Nẵng","Giải Thích Ngữ Pháp Tiếng Anh - Tái Bản 2022", "item_46", 4, 4.5, 143000, "Sách Giải Thích Ngữ Pháp Tiếng Anh, tác Mai Lan Hương – Hà Thanh Uyên, là cuốn sách ngữ pháp đã được phát hành và tái bản rất nhiều lần trong những năm qua. Giải Thích Ngữ Pháp Tiếng Anh được biên soạn thành 9 chương, đề cập đến những vấn đề ngữ pháp từ cơ bản đến nâng cao, phù hợp với mọi trình độ. Các chủ điểm ngữ pháp trong từng chương được biên soạn chi tiết, giải thích cặn kẽ các cách dùng và quy luật mà người học cần nắm vững. Sau mỗi chủ điểm ngữ pháp là phần bài tập đa dạng nhằm giúp người học củng cố lý thuyết."));
        list5.add(new BookList(47, "Giáo khoa - Tham khảo", "Malcome Mann, Steve Taylore-Knowles",	"Hồng Đức","Destination B2 - Grammar And Vocabulary with Answer Key", "item_47", 3, 4, 113230, "Cuốn sách Destination B2 do Công ty Cổ phần sách MCBooks giữ bản quyền xuất bản tại Việt Nam theo Hợp đồng chuyển giao bản quyền với Nhà xuất bản Macmillan. Bộ sách cung cấp từ vựng và ngữ pháp tiếng Anh cần thiết nhất dành cho người học đang có ý định thi các kỳ thi ở Level B1, B2, C1, C2 theo khung tham chiếu châu Âu và mong muốn cải thiện năng lực tiếng Anh của bản thân. Toàn bộ kiến thức trong sách được viết bằng tiếng Anh bao gồm 28 Unit. Trong đó, cứ một Unit học về ngữ pháp thì có một Unit học về từ vựng theo chủ đề."));
        list5.add(new BookList(48, "Giáo khoa - Tham khảo", "Nhiều Tác Giả",	"Đại Học Sư Phạm Thành Phố Hồ Chí Minh","Vở Bài Tập Tiếng Việt 4 - Tập 2", "item_48", 2, 3, 18050, "Sách giáo khoa Vở Bài Tập Tiếng Việt 4 - Tập 2 thuộc bộ sách Cánh Diều, được biên soạn dựa trên quan điểm, mục tiêu của Chương trình Giáo dục phổ thông 2018 nhằm đáp ứng yêu cầu phát triển phẩm chất và năng lực của trẻ."));
        list5.add(new BookList(49, "Giáo khoa - Tham khảo", "ThS Phan Hoàng Văn",	"NXB Đại Học Quốc Gia TP.HCM","500 Bài Tập Vật Lí Trung Học Cơ Sở", "item_49", 0, 3.5, 92800, "Là tài liệu tham khảo cần thiết cho những học sinh muốn tìm hiểu kĩ về môn khoa học thú vị này. Đây là tài liệu dùng để ôn tập, chuẩn bị cho các kì thi học sinh giỏi, tuyển sinh vào các trường chuyên. Quyển sách là một tài liệu bổ ích cho những học sinh yêu thích môn Vật lí có điều kiện học tập tốt nhất. Đồng thời, cuốn sách có thể giúp các thầy cô giáo có thêm tài liệu bổ sung dùng để bồi dưỡng học sinh."));
        list5.add(new BookList(50, "Giáo khoa - Tham khảo", "Nhiều Tác Giả",	"Dân Trí","25 Đề Đánh Giá Năng Lực Đại Học Quốc Gia Tp. Hồ Chí Minh", "item_50", 1, 3, 188000, "Sách cung cấp kiến thức, phương pháp và kỹ năng làm các dạng bài có trong đề thi ĐGNL. Sách có phần giải chi tiết từng câu hỏi kèm video chữa bài theo mã ID. Đề thi gồm đầy đủ các môn: Tiếng Việt, Tiếng anh, Toán học, Vật lý, Hóa học, Sinh học, Địa lý, Lịch sử, chính trị, xã hội. Số lượng bài tập theo các đề giống với 1 đề thi thật, giúp các bạn làm quen với cấu trúc đề thi ĐGNL. Ngoài ra, sách có cung cấp đáp án các câu hỏi để học sinh thuận tiện đối chiếu và tự chấm điểm."));
    }
}