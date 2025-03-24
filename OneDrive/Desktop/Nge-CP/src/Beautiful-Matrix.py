def beautifulMatrix() :
    matrix = [list(map(int, input().split())) for _ in range (5)] 
    # input matrix kotak dengan list map 
    
    for i in range(5) : 
        for j in range (5) : 
            if matrix[i][j] == 1 :  # cek kembali jika yang diakses =1 
                print(abs(i-2) + abs(j-2))
                # dengan nilai absolut akan mengembalikan arah menuju (2,2) 
                
                # misalkan, dengan ilustrasi matematika 
                # ditemukan 1 dititik 1,1 dimana tengah nya tetap 2,2 (karena luas 5x5 dengan dimulai 0,0 menuju 4,4)
                # 1 + x = 2 -> x = 2 - 1 , atau agar berlaku sebaliknya, diberi absolut 
                # demikian juga y
        
beautifulMatrix()
    
# Perbaikan -- gunakan salah satu

def beautifulMatrix2() : 
    # Membaca input baris per baris
    for i in range(5):
        row = list(map(int, input().split()))  # baca satu baris
        if 1 in row:  # Jika 1 ditemukan dalam baris tersebut
            # Menentukan posisi angka 1
            j = row.index(1)  # Mencari kolom angka 1
            # Menghitung jumlah langkah vertikal dan horizontal
            print(abs(i - 2) + abs(j - 2))
            return  # Setelah menemukan angka 1, tidak perlu melanjutkan iterasi

beautifulMatrix2()