def QatS():
    n, t = map(int, input().split())  # n = panjang antrian, t = jumlah detik
    s = list(input().strip())  # Menyimpan antrian dalam list
    
    for _ in range(t):  # Proses selama t detik
        i = 0
        while i < n - 1:  # Loop hingga indeks kedua terakhir
            if s[i] == 'B' and s[i + 1] == 'G':
                # Tukar posisi B dan G
                s[i], s[i + 1] = s[i + 1], s[i]
                # Lompat satu langkah untuk menghindari pertukaran ganda
                i += 1
            i += 1

    # Menampilkan hasil akhir antrian
    print("".join(s))

QatS()
