# IF3210-2022-Android-33

## Deskripsi Aplikasi
Aplikasi PerluDilindungi adalah sebuah super app untuk segala hal dan kebutuhan yang berkaitan dengan COVID-19, dari penelusuran berita terbaru, pencarian daftar fasilitas serta keterangan fasilitas kesehatan, hingga sistem check in dengan scanner kode QR.

## Cara Kerja Aplikasi
Aplikasi dibangun diatas platform Android dengan mengimplementasikan komponen/elemen/konsep-konsep dasar Android seperti RecyclerView untuk menampilkan list berita serta list fasilitas kesehatan, kemudian berbasis fragment dimana terdapat resource file .xml yang mendeskripsikan struktur antarmuka, dan file .kt berkaitan yang berfungsi untuk mendefinisikan ViewModel serta kelas kotlin untuk antarmuka tadi.

Pemanggilan data dinamik dilakukan dengan dua cara, pertama adalah dengan pemanggilan API yang dilakukan dengan memanfaatkan HTTP client Retrofit, yang kedua yakni dari database berbasis Room yakni sebuah dependensi yang merupakan abstraksi di atas basis data SQLite.

## Library yang Digunakan
### Retrofit

HTTP Client dengan dokumentasi terbanyak dan paling marak digunakan
### Coroutines

Library yang membawa suite fungsi-fungsi konkuren dan memungkinkan fungsi tersuspensi pada thread secara asinkron (biasa digunakan untuk menunggu API response)
### AndroidX (Android Extensions)

Library extension dengan banyak sub-dependency lain yang digunakan seperti komponen RecyclerView dan lainnya (digunakan karena cocok untuk menampilkan list dan diperintahkan pula pada spesifikasi)
### Room

Abstraksi di atas basis data SQLite yang memudahkan penyetelan basis data apabila dibandingkan dengan menggunakan SQLite secara native di Android
### Glide
Library untuk melakukan loading image resource pada suatu ImageView

## Screenshot Aplikasi
<img width="420" alt="image" src="https://user-images.githubusercontent.com/53634665/156866232-c991dd57-0e62-41e8-8beb-78377b5c5d64.png">

<img width="420" alt="image" src="https://user-images.githubusercontent.com/53634665/156866233-e7a515f8-98ca-4a7a-ba1d-a4a8814ebd9e.png">

## Pembagian Kerja
| NIM      | Nama           | Tugas                                    |
|----------|----------------|------------------------------------------|
| 13519064 | Aditya Bimawan | Daftar Faskes, Bookmark, QR Code         |
| 13519078 | James Chandra  | Berita, Detail Faskes, Bookmark, QR Code |
| 13519101 | Stefanus       | -                                        |
