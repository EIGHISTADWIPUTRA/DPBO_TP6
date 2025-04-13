# Tugas Praktikum 6 DPBO - Game Bombardino Crocodilo (Flappy Bird)

## Janji

Saya Muhammad Bintang Eighista Dwiputra dengan NIM 2304137 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## Deskripsi
Proyek ini merupakan implementasi sederhana dari game *Flappy Bird* menggunakan Java Swing. Dalam versi ini, karakter utama bernama **Bombardino Crocodilo** akan melewati pipa-pipa tanpa menyentuhnya.

---

## 🧩 Struktur File

- `App.java` : Entry point dari aplikasi. Mengatur tampilan frame utama dan timer game.
- `FlappyBird.java` : Panel utama game. Berisi logika utama permainan, render grafik, dan sistem skor.
- `Player.java` : Representasi karakter pemain (burung/krokodil).
- `Pipe.java` : Representasi objek rintangan (pipa) atas dan bawah.

---

## 🧠 Desain Program

### 🔹 `App.java`
**Deskripsi:** Menginisialisasi JFrame dan menjalankan loop utama game.

**Atribut utama:**
- `JFrame frame` : Frame utama tempat game dijalankan.
- `Timer timer` : Timer yang menjalankan update game setiap 20 ms (~50 FPS).

---

### 🔹 `FlappyBird.java`
**Deskripsi:** Komponen utama game. Menangani tampilan, logika game, dan input.

**Atribut:**
- `frameWidth`, `frameHeight` : Ukuran jendela permainan.
- `backgroundImg`, `burungImg`, `PipaBawahImg`, `PipaAtasImg` : Gambar yang digunakan dalam game.
- `playerStartPosX`, `playerStartPosY` : Posisi awal pemain.
- `playerWidth`, `playerHeight` : Ukuran karakter pemain.
- `player` : Objek pemain dari kelas `Player`.
- `pipeStartPosX`, `pipeStartPosy`, `pipeWidth`, `pipeHeight` : Properti pipa.
- `pipes` : List dari objek `Pipe` yang aktif.
- `pipesCooldown` : Timer untuk memunculkan pipa baru secara berkala.
- `scoreLabel` : Label untuk menampilkan skor.
- `score` : Nilai skor yang didapatkan pemain.
- `isGameOver` : Status apakah permainan sudah selesai.
- `passedPipes` : Daftar id pipa yang sudah dilewati untuk menghitung skor.
- `gravity` : Nilai gravitasi yang diterapkan ke pemain.

---

### 🔹 `Player.java`
**Deskripsi:** Kelas representasi karakter utama (burung/krokodil).

**Atribut:**
- `posX`, `posY` : Posisi karakter di layar.
- `width`, `height` : Ukuran karakter.
- `image` : Gambar sprite karakter.
- `velocityY` : Kecepatan vertikal karakter (untuk efek gravitasi dan loncatan).

---

### 🔹 `Pipe.java`
**Deskripsi:** Representasi pipa atas dan bawah sebagai rintangan.

**Atribut:**
- `posX`, `posY` : Posisi pipa di layar.
- `width`, `height` : Ukuran pipa.
- `image` : Gambar sprite pipa.
- `velocityX` : Kecepatan horizontal pipa (bergerak ke kiri).
- `passed` : Menandakan apakah pemain sudah melewati pipa (digunakan untuk skor).

---

## 🔁 Alur Permainan

1. Program dimulai dengan menjalankan `App.java`.
2. `JFrame` dibuat dan `FlappyBird` ditambahkan ke dalamnya.
3. Timer `actionPerformed()` akan:
   - Memanggil `move()` → memperbarui posisi pemain dan pipa.
   - Mengecek tabrakan via `checkCollision()`.
   - Menambah skor jika pemain berhasil melewati pipa via `updateScore()`.
   - Memanggil `repaint()` untuk menggambar ulang panel.
4. Jika terjadi tabrakan, game over dan pemain ditampilkan skor akhir.
5. Pemain dapat memulai ulang dengan menekan tombol **R**.

---

## 🎮 Kontrol

- `SPACE`: Meloncat.
- `R`: Restart game setelah Game Over.

---

## 📷 Dokumentasi Saat Program Dijalankan

- Saat dimulai:
  - Latar belakang dan pemain ditampilkan.
  - Pipa mulai muncul setiap 1.5 detik dari sisi kanan.
- Saat pemain menabrak pipa atau tanah:
  - Muncul pesan `Game Over! Score: X`.
  - Game berhenti, dan pipa tidak lagi ditambahkan.
- Setelah menekan `R`:
  - Posisi dan skor direset.
  - Game dimulai dari awal.

---

## 📁 Asset

Letakkan gambar berikut dalam folder `Assets/`:
- `background.png` — Gambar latar belakang.
- `bombardino_crocodilo.png` — Gambar karakter utama.
- `upperPipe.png` — Gambar pipa atas.
- `lowerPipe.png` — Gambar pipa bawah.

---

## 🚀 Dokumentasi
![Saat game berjalan](https://github.com/user-attachments/assets/35a79b6b-6cca-48f0-bb8d-3f093d479573)

![Saat game over](https://github.com/user-attachments/assets/88161fb3-d8d3-4760-a92d-a1ecb50bbf1e)


