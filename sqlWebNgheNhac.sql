CREATE DATABASE WebNgheNhac;
GO


GO

GO
USE master;
ALTER DATABASE WebNgheNhac SET SINGLE_USER WITH ROLLBACK IMMEDIATE;

GO
ALTER DATABASE WebNgheNhac COLLATE Vietnamese_CI_AS;
GO

ALTER DATABASE WebNgheNhac SET MULTI_USER;
GO
SELECT DATABASEPROPERTYEX('WebNgheNhac', 'Collation') AS DatabaseCollation;

GO
USE WebNgheNhac;

-- Bảng Roles
CREATE TABLE Roles (
    roleId NVARCHAR(50) PRIMARY KEY,
    tenRole NVARCHAR(50) NOT NULL
);

GO
-- Bảng Users
CREATE TABLE Users (
    username NVARCHAR(50) PRIMARY KEY,
    password NVARCHAR(255) NULL,
    hoTen NVARCHAR(50) NULL,
    email NVARCHAR(100) NOT NULL,
    hinhAnh NVARCHAR(255) NULL,
    trangThai BIT NOT NULL,
    roleId NVARCHAR(50) NOT NULL,
    FOREIGN KEY (roleId) REFERENCES Roles(roleId)
);

GO
-- Bảng GoiPremium
CREATE TABLE GoiPremium (
    goiId INT IDENTITY PRIMARY KEY,
    tenGoi NVARCHAR(50) NOT NULL,
    thoiHan INT NOT NULL,
    gia DECIMAL(18,2) NOT NULL,
    trangThai BIT NOT NULL
);

GO
-- Bảng ThanhToan
CREATE TABLE ThanhToan (
    thanhToanId INT IDENTITY PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    goiId INT NOT NULL,
    soTien DECIMAL(18,2) NOT NULL,
    phuongThuc NVARCHAR(50) NOT NULL,
    ngayThanhToan DATE NOT NULL,
    trangThai BIT NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(username),
    FOREIGN KEY (goiId) REFERENCES GoiPremium(goiId)
);

GO
-- Bảng UserPremium
CREATE TABLE UserPremium (
    id INT IDENTITY PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    goiId INT NOT NULL,
    ngayBatDau DATE NOT NULL,
    ngayHetHan DATE NOT NULL,
    trangThai BIT NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(username),
    FOREIGN KEY (goiId) REFERENCES GoiPremium(goiId)
);

GO
-- Bảng Playlist
CREATE TABLE Playlist (
    playlistId INT IDENTITY PRIMARY KEY,
    tenPlaylist NVARCHAR(50) NOT NULL,
    username NVARCHAR(50) NOT NULL,
	hinhPlaylist NVARCHAR(255) NULL,
    trangThai BIT NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(username)
);

GO
-- Bảng TheLoai
CREATE TABLE TheLoai (
    theLoaiId INT IDENTITY PRIMARY KEY,
    tenTheLoai NVARCHAR(50) NOT NULL
);

GO

-- Bảng NgheSi
CREATE TABLE NgheSi (
    ngheSiId NVARCHAR(50) PRIMARY KEY,
    tenNgheSi NVARCHAR(50) NOT NULL,
    gioiThieu NVARCHAR(MAX) NULL,
	hinhNgheSi NVARCHAR(255) NULL
);

GO

-- Bảng Album
CREATE TABLE Album (
    albumId INT IDENTITY PRIMARY KEY,
    tenAlbum NVARCHAR(50) NOT NULL,
    ngayTao DATE NULL,
    hinhAlbum NVARCHAR(255) NULL,
	ngheSiId NVARCHAR(50) NOT NULL,
	spotifyId NVARCHAR(MAX) NOT NULL,
	FOREIGN KEY (ngheSiId) REFERENCES NgheSi(ngheSiId)
);

GO

-- Bảng BaiHat
CREATE TABLE BaiHat (
    baiHatId INT IDENTITY PRIMARY KEY,
    tenBaiHat NVARCHAR(100) NOT NULL,
    theLoaiId INT NULL,
    spotifyId NVARCHAR(MAX) NOT NULL,
    loiBaiHat NVARCHAR(MAX) NULL,
	hinhBaiHat NVARCHAR(255) NULL,
    ngayTao DATE NULL,
    ngheSiId NVARCHAR(50) NOT NULL,
    trangThai BIT NOT NULL,
    FOREIGN KEY (theLoaiId) REFERENCES TheLoai(theLoaiId),
    FOREIGN KEY (ngheSiId) REFERENCES NgheSi(ngheSiId)
);

GO

-- Bảng ChiTietPlaylist
CREATE TABLE ChiTietPlaylist (
    id INT IDENTITY PRIMARY KEY,
    playlistId INT NOT NULL,
    baiHatId INT NOT NULL,
    soThuTu INT NOT NULL,
    FOREIGN KEY (playlistId) REFERENCES Playlist(playlistId),
    FOREIGN KEY (baiHatId) REFERENCES BaiHat(baiHatId)
);

GO
-- Bảng DanhGia
CREATE TABLE DanhGia (
    danhGiaId INT IDENTITY PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    baiHatId INT NOT NULL,
    diemSo INT NOT NULL,
    ngayTao DATE NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(username),
    FOREIGN KEY (baiHatId) REFERENCES BaiHat(baiHatId)
);

GO
-- Bảng BinhLuan
CREATE TABLE BinhLuan (
    binhLuanId INT IDENTITY PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    baiHatId INT NOT NULL,
    noiDungBL NVARCHAR(500) NOT NULL,
    ngayBinhLuan DATE NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(username),
    FOREIGN KEY (baiHatId) REFERENCES BaiHat(baiHatId)
);

GO
-- Bảng LichSuNghe
CREATE TABLE LichSuNghe (
    lsNgheId INT IDENTITY PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    baiHatId INT NOT NULL,
    thoiGianNghe DATETIME NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(username),
    FOREIGN KEY (baiHatId) REFERENCES BaiHat(baiHatId)
);



--dữ liệu TheLoai-- 
INSERT INTO TheLoai (tenTheLoai)
VALUES (N'Ballad'),
       (N'Rap'),
       (N'Bolero');

	   GO

--dữ liệu	 nghệ sĩ--
-- bỏ ngày ra mắt nghệ sĩ --
INSERT INTO NgheSi (ngheSiId, tenNgheSi, gioiThieu, hinhNgheSi)
VALUES 
    ('NS001', N'HIEUTHUHAI', N'Ai cũng phải bắt đầu từ đâu đó', 'nghesi_hieuthuhai.jpg'),
    ('NS002', N'B Ray', N'B Ray là một rapper chuyên nghiệp của Underground Việt Nam', 'nghesi_bray.jpg'),
    ('NS003', N'Sơn Tùng M-TP', N'Nguyễn Thanh Tùng (sinh ngày 5 tháng 7 năm 1994), thường được biết đến với nghệ danh Sơn Tùng M-TP, là một nam ca sĩ kiêm nhạc sĩ sáng tác bài hát, nhà sản xuất thu âm, rapper và diễn viên người Việt Nam', 'nghesi_sontungmtp.jpg'),
    ('NS004', N'Dương Domic', N'Dương Domic (Trần Đăng Dương), sinh năm 2000 tại Hải Dương. Nam ca sĩ được đào tạo theo tiêu chuẩn idol Kpop tại IF Entertainment trong 3 năm trước khi chính thức phát hành sản phẩm âm nhạc đầu tay vào tháng 12/2021', 'album_dulieuquy.jpg'),
    ('NS005', N'RHYDER', N'RHYDER, nhân tố nổi bật từ Rap Việt mùa 3, sẽ chính thức phát hành single đầu tay mang tựa đề "Chịu Cách Mình Nói Thua". Đây không chỉ là một sản phẩm âm nhạc mới mà còn là một câu chuyện tâm huyết về tình yêu và mất mát', 'nghesi_rhyder.jpg'),
    ('NS006', N'Pháp Kiều', N'Pháp Kiều bắt đầu yêu thích rap thông qua những sản phẩm của Nicki Minaj. Sau đó, khi Rap Việt ra mắt và giới thiệu thêm nhiều nghệ sĩ rap Việt Nam, như HIEUTHUHAI và Suboi, Pháp Kiều đã quyết định thử sức với rap và đặt mục tiêu của mình vào lĩnh vực này.', 'nghesi_phapkieu.jpg'),
    ('NS007', N'ERIK', N'Erik - Lê Trung Thành (sinh ngày 13 tháng 10 năm 1997), thuộc công ty EnS Entertainment. Anh là một ca sĩ người Việt Nam. Anh được biết đến lần đầu tiên khi tham gia The Voice Kids Vietnam năm 2013, và sau đó là thành viên của nhóm nhạc Monstar vào năm 2016. Hiện tại, anh là một nghệ sĩ solo.', 'nghesi_erik.jpg'),
    ('NS008', N'Vũ', N'Tôi là Vũ', 'nghesi_vu.jpg'),
    ('NS009', N'Obito', N'Obito (@youngtobieedasick) là một nghệ sĩ Việt Nam, hiện đang hoạt động tại TP. Hồ Chí Minh.', 'nghesi_obito.jpg'),
    ('NS010', N'RPT MCK', N'Nghiêm Vũ Hoàng Long, còn được biết đến với nghệ danh MCK, là một rapper/ca sĩ-nhạc sĩ đến từ Hà Nội, Việt Nam. Sự nghiệp âm nhạc của anh bắt đầu vào đầu năm 2018 với tư cách là một ca sĩ/nhạc sĩ độc lập dưới nghệ danh Ngơ.', 'nghesi_mck.jpg'),
    ('NS011', N'Pháo', N'thường được biết đến với nghệ danh Pháo hay Pháo Northside, là một nữ rapper và ca sĩ kiêm nhạc sĩ sáng tác ca khúc người Việt Nam. Cô bắt đầu được biết đến rộng rãi qua ca khúc "2 phút hơn" (2020)', 'nghisi_phao.jpg'),
	('NS012', N'JustaTee', N'JustaTee (Nguyễn Thanh Tuấn) là một trong những nghệ sĩ tiên phong của dòng nhạc R&B/Hip-hop tại Việt Nam.', 'nghesi_justatee.jpg'),
    ('NS013', N'Binz', N'Binz (Lê Nguyễn Trung Đan) là một rapper và nhạc sĩ người Việt Nam, trực thuộc SpaceSpeakers Group.', 'nghesi_binz.jpg'),
    ('NS014', N'Văn Mai Hương', N'Văn Mai Hương là một nữ ca sĩ tài năng, nổi tiếng từ cuộc thi Vietnam Idol và có nhiều bản hit đáng nhớ.', 'nghesi_vanmaihuong.jpg'),
    ('NS015', N'Hoàng Dũng', N'Hoàng Dũng là một ca sĩ kiêm nhạc sĩ nổi bật với dòng nhạc ballad nhẹ nhàng, từng gây sốt với bản hit "Nàng Thơ".', 'nghesi_hoangdung.jpg'),
    ('NS016', N'Hoàng Thùy Linh', N'Hoàng Thùy Linh là nữ ca sĩ, diễn viên nổi bật của V-pop với nhiều bản hit mang màu sắc dân gian hiện đại.', 'nghesi_hoangthuylinh.jpg'),
    ('NS017', N'Tóc Tiên', N'Tóc Tiên là một nữ ca sĩ người Việt Nam, nổi tiếng với phong cách âm nhạc sôi động và cá tính mạnh mẽ.', 'nghesi_toctien.jpg'),
    ('NS018', N'Suboi', N'Suboi là một trong những nữ rapper tiên phong của Việt Nam, được biết đến với phong cách rap độc đáo và cá tính.', 'nghesi_suboi.jpg'),
    ('NS019', N'Den Vâu', N'Den Vâu là một rapper nổi tiếng với những ca khúc mang đậm chất đời sống, giản dị nhưng đầy ý nghĩa.', 'nghesi_denvau.jpg'),
    ('NS020', N'Wren Evans', N'Wren Evans là một ca sĩ trẻ tài năng, kết hợp nhuần nhuyễn giữa nhạc pop, R&B và indie.', 'nghesi_Wren Evans.jpg'),
    ('NS021', N'Kai Đinh', N'Kai Đinh là một ca sĩ kiêm nhạc sĩ sáng tác, nổi tiếng với những bản nhạc có chiều sâu cảm xúc.', 'nghesi_kaidinh.jpg'),
    ('NS023', N'Mono', N'Mono (Nguyễn Việt Hoàng) là em trai của Sơn Tùng M-TP, ra mắt với phong cách âm nhạc trẻ trung, hiện đại.', 'nghesi_mono.jpg'),
    ('NS024', N'GREY D', N'GREY D là một ca sĩ và nhạc sĩ, cựu thành viên của nhóm nhạc Monstar, được biết đến với các ca khúc như "Va Vào Giai Điệu Này".', 'nghesi_greyd.jpg'),
    ('NS025', N'Hà Anh Tuấn', N'Hà Anh Tuấn là một ca sĩ nổi tiếng với chất giọng nam trầm ấm áp và phong cách biểu diễn lịch lãm.', 'nghesi_haanhtuan.jpg'),
    ('NS026', N'Mỹ Tâm', N'Mỹ Tâm là một trong những ca sĩ hàng đầu của Việt Nam, có sự nghiệp dài lâu và nhiều bản hit bất hủ.', 'nghesi_mytam.jpg'),
    ('NS027', N'Bích Phương', N'Bích Phương là một ca sĩ nổi bật với dòng nhạc pop-ballad và hình tượng nữ tính, duyên dáng.', 'nghesi_bichphuong.jpg'),
    ('NS029', N'SOOBIN', N'SOOBIN là một ca sĩ kiêm nhạc sĩ nổi bật với dòng nhạc R&B và rap, là thành viên của SpaceSpeakers.', 'nghesi_soobin.jpg');
    
	GO

-- Dữ liệu Album
INSERT INTO Album (tenAlbum, ngayTao, hinhAlbum, spotifyId, ngheSiId)
VALUES 
    (N'Ai cũng phải bắt đầu từ đâu đó', '2023-10-16', 'album_aicungphaibatdautudaudo.jpg', '4faMbTZifuYsBllYHZsFKJ?utm_source=generator', 'NS001'),
    (N'Loser2Love', '2020-02-14', 'album_loser2love.jpg', '6FN36Q21wxUPgvNWbSRqLY?utm_source=generator', 'NS002'),
    (N'm-tp M-TP', '2017-04-01', 'album_mtpMTP.jpg', '1AaxmI2e1HRhbwe9XJGPnT?utm_source=generator', 'NS003'),
    (N'Dữ liệu quý', '2024-11-22', 'album_dulieuquy.jpg', '10Dwjqs7dJNxn2g1PkvRCw?utm_source=generator', 'NS004'),
    (N'Chịu cách mình nói thua', '2023-11-22', 'album_chiucachminhnoithua.jpg', '6H01GjpEb6taXvwiqEzpFy?utm_source=generator', 'NS005'),
    (N'Cung tên tình yêu', '2024-10-25', 'album_cungtentinhyeu.jpg', '6ep87XWMrIAwAgKkEnNqEG?utm_source=generator', 'NS006'),
    (N'Dù cho tận thế', '2025-03-02', 'album_duchotanthe.jpg', '4jfETKK8XsrSmr4EJAjjTu?utm_source=generator', 'NS007'),
    (N'Bảo tàng của nuối tiếc', '2024-09-07', 'album_baotangnuoitiec.jpg', '3pprs1r3mH3UhU23TUHBWJ?utm_source=generator', 'NS008'),
    (N'Đánh đổi', '2023-10-10', 'album_danhdoi.jpg', '03ZYR4zwCrkSsXTROnK2d0?utm_source=generator', 'NS009'),
    (N'99%', '2023-03-02', 'album_99.jpg', '1vi1WySkgPGkbR8NnQzlXu?utm_source=generator', 'NS010'),
    (N'Keep Cầm Ca', '2024-11-27', 'album_keepcamca.jpg', '7r8oe0vonpVK6QL64rrbxJ?utm_source=generator', 'NS013'),
    (N'22', '2022-8-18', 'album_22.jpg', '40Z8f0FLhVboUdBqnlHiJp?utm_source=generator', 'NS023'),
	(N'Bật nó lên', '2024-6-25', 'album_batnolen.jpg', '5tlCVkYaDAmAtJ5YxejpWi?utm_source=generator', 'NS029'),
	(N'Loi Choi: The Neo Pop Punk', '2023-12-17', 'album_loichoi.jpg', '1c4nTHI2hreFeF5P37wf4f?utm_source=generator', 'NS020'),
	(N'dongvui harmony', '2022-09-11', 'album_dongvuiharmony.jpg', '3Z09WuEkWJTrQRJ7aFRY51?utm_source=generator', 'NS019');

	GO

-- Dữ liệu bài hát
INSERT INTO BaiHat (tenBaiHat, theLoaiId, spotifyId, loiBaiHat, hinhBaiHat, ngayTao, ngheSiId, trangThai)
VALUES 
    (N'Không Thể Say', '02', '1K0HQ30Wc11okzlcnFA7Ub?utm_source=generator', NULL, 'baihat_khongthesay.jpg', '2023-10-16', 'NS001', 1),
    (N'Mùa Hè Năm Đó', '02', '76UWLyJm10LudXC3rdYSRO?utm_source=generator', NULL, 'baihat_muahenamdo.jpg', '2020-02-14', 'NS002', 1),
    (N'Đừng Làm Trái Tim Anh Đau', '01', '31VNCmwspR7nVJ6kruUuJt?utm_source=generator', NULL, 'baihat_dunglamtraitimanhdau.jpg', '2017-04-01', 'NS003', 1),
    (N'Mất Kết Nối', '01', '3CmacJj7VC4W6daC8BWd0h?utm_source=generator', NULL, 'baihat_matketnoi.jpg', '2024-11-22', 'NS004', 1),
    (N'Chịu Cách Mình Nói Thua', '02', '3ZbZtdEw9U0uZW4tZItIwq?utm_source=generator', NULL, 'baihat_chiucachminhnoithua.jpg', '2023-11-22', 'NS005', 1),
    (N'Cung Tên Tình Yêu', '02', '0VNF2lepEjjJTpNpdDKEAT?utm_source=generator', NULL, 'baihat_cungtentinhyeu.jpg', '2024-10-25', 'NS006', 1),
    (N'Dù Cho Tận Thế', '01', '0oSc7X1uRQGuEqNRtXEeQL?utm_source=generator', NULL, 'baihat_duchotanthe.jpg', '2025-03-02', 'NS007', 1),
    (N'Bình Yên', '01', '41DlPJXKTCypXdf2eSqa03?utm_source=generator', NULL, 'baihat_binhyen.jpg', '2024-09-27', 'NS008', 1),
    (N'Đánh Đổi', '02', '1UQNU5O9VttUgO16pxUnjw?utm_source=generator', NULL, 'baihat_danhdoi.jpg', '2023-10-10', 'NS009', 1),
    (N'Chìm Sâu', '02', '7jLSThU5Kg1RWt19Leiaxm?utm_source=generator', NULL, 'baihat_chimsau.jpg', '2023-03-02', 'NS010', 1),
    (N'Sự Nghiệp Chướng', '02', '1bP5rXoqc7V9ZQfPlaANKI?utm_source=generator', NULL, 'baihat_sunghiepchuong.jpg', '2023-10-10', 'NS011', 1),
	(N'2 Phút Hơn','02' , '6h8ZafZJnbIpCaEHnzZYRF?utm_source=generator', NULL, 'baihat_2phuthon.jpg', '2020-07-01', 'NS011', 1), -- Pháo  
    (N'Thằng Điên', '02', '27Z9T0mSWHYWSj9SNOGvyt?utm_source=generator', NULL, 'baihat_thangdien.jpg', '2018-09-25', 'NS012', 1), -- JustaTee  
    (N'Bigcityboi','02', '3sV8ExJeOGXbanczafavZv?utm_source=generator', NULL, 'baihat_bigcityboi.jpg', '2020-07-05', 'NS013', 1), -- Binz  
    (N'Gene','02', '1UL2QMgWPeNCJhznvr99Jk?utm_source=generator', NULL, 'album_keepcamca.jpg', '2019-05-20', 'NS013', 1), -- Binz  
    (N'Simple Love','01', '1hFZdk0jIkrMEribQHxGhP?utm_source=generator', NULL, 'nghesi_obito.jpg', '2019-10-07', 'NS014', 1), -- Touliver  
    (N'Nàng Thơ', '01', '1s7d2cdaZuktw5YSoDMHMu?utm_source=generator', NULL, 'baihat_nangtho.jpg', '2020-08-06', 'NS015', 1), -- Hoàng Dũng  
    (N'Gieo Quẻ','01', '7lkWAB0Ru7WEa0mkdgiqJG?utm_source=generator', NULL, 'nghesi_hoangthuylinh.jpg', '2022-01-26', 'NS016', 1), -- Hoàng Thùy Linh  
    (N'Có Ai Thương Em Như Anh','01', '7AD5F85waSb3yZxZxEXhJM?utm_source=generator', NULL, 'baihat_coaithuongemnhuanh.jpg', '2018-07-11', 'NS017', 1), -- Tóc Tiên  
    (N'N-Sao?', '02', '09eYSoem8gQWelNj1G9Aqa?utm_source=generator', NULL, 'baihat_n-sao.jpg', '2020-09-15', 'NS018', 1), -- Suboi  
    (N'Mười Năm', '02', '4FT18tLTmf3QNBzG5r6OTj?utm_source=generator', NULL, 'baihat_muoinam.jpg', '2019-11-11', 'NS019', 1), -- Đen Vâu  
    (N'Lễ đường', '01', '3y66li7DIrH7HLKIZzxR5H?utm_source=generator', NULL, 'baihat_leduong.jpg', '2022-10-14', 'NS021', 1), -- Kai Đinh  
    (N'Waiting For You', '01', '6GICR3XCKLGs1llkGTo17f?utm_source=generator', NULL, 'baihat_waitingforyou.jpg', '2022-08-12', 'NS023', 1), -- Mono  
    (N'Dự báo thời tiết hôm nay mưa', '01', '65cM1nyMf9pFDZVV8xmPX9?utm_source=generator', NULL, 'baihat_dubaothoitiethomnaymua.jpg', '2023-03-24', 'NS024', 1), -- GREY D  
    (N'Tháng Tư Là Lời Nói Dối Của Em', '01', '53zusvCJkyGYjEZ6bnLIjh?utm_source=generator', NULL, 'baihat_thangtulaloinoidoicuaem.jpg', '2016-04-01', 'NS025', 1), -- Hà Anh Tuấn  
    (N'Người Hãy Quên Em Đi','01', '1rJyVbXb3njXbN3lokllKf?utm_source=generator', NULL, 'baihat_nguoihayquenemdi.jpg', '2017-12-25', 'NS026', 1), -- Mỹ Tâm  
    (N'Bùa Yêu','01', '1ZrADntHAYV84R2y1Op3Eg?utm_source=generator', NULL, 'baihat_buayeu.jpg', '2018-05-12', 'NS027', 1); -- Bích Phương 

INSERT INTO Roles (roleId, tenRole)
VALUES
    ('ROLE_USER', 'ROLE_USER'),
    ('ROLE_MANAGER', 'ROLE_MANAGER'),
    ('ROLE_ADMIN', 'ROLE_ADMIN');

INSERT INTO Users (username, password, hoTen, email, hinhAnh, trangThai, roleId)
VALUES
    ('admin', '123', 'Nguyễn Văn A', 'admin@example.com', NULL, 1, 'ROLE_ADMIN'),
    ('manager', '123', 'Trần Thị B', 'manager@example.com', NULL, 1, 'ROLE_MANAGER'),
    ('user', '123', 'Lê Minh C', 'normaluser@example.com', NULL, 1, 'ROLE_USER');

GO
INSERT INTO GoiPremium (tenGoi, thoiHan, gia, trangThai)
VALUES 
    (N'Gói 1 tháng', 30, 99000, 1),
    (N'Gói 3 tháng', 90, 199000, 1),
    (N'Gói 6 tháng', 180, 299000, 1),
    (N'Gói 12 tháng', 365, 499000, 1);

GO 
INSERT INTO ThanhToan (username, goiId, soTien, phuongThuc, ngayThanhToan, trangThai)
VALUES 
    (N'user', 1, 99000, N'Credit Car', '2024-03-01', 1),
    (N'user', 2, 199000, N'Momo', '2024-03-15', 1),
    (N'user', 2, 299000, N'Bank Transfer', '2024-04-01', 1);
GO
INSERT INTO DanhGia (username, baiHatId, diemSo, ngayTao)
VALUES 
    (N'user', 1, 5, '2024-03-02'),
    (N'user', 2, 4, '2024-03-16'),
    (N'user', 3, 5, '2024-04-02');



GO

-- Bảng ThanhToan
ALTER TABLE ThanhToan
ADD CONSTRAINT FK_ThanhToan_Users FOREIGN KEY (username) 
REFERENCES Users(username) ON DELETE CASCADE;

ALTER TABLE ThanhToan
ADD CONSTRAINT FK_ThanhToan_GoiPremium FOREIGN KEY (goiId) 
REFERENCES GoiPremium(goiId) ON DELETE CASCADE;

-- Bảng UserPremium
ALTER TABLE UserPremium
ADD CONSTRAINT FK_UserPremium_Users FOREIGN KEY (username) 
REFERENCES Users(username) ON DELETE CASCADE;

ALTER TABLE UserPremium
ADD CONSTRAINT FK_UserPremium_GoiPremium FOREIGN KEY (goiId) 
REFERENCES GoiPremium(goiId) ON DELETE CASCADE;

-- Bảng Playlist
ALTER TABLE Playlist
ADD CONSTRAINT FK_Playlist_Users FOREIGN KEY (username) 
REFERENCES Users(username) ON DELETE CASCADE;

-- Bảng Album
ALTER TABLE Album
ADD CONSTRAINT FK_Album_NgheSi FOREIGN KEY (ngheSiId) 
REFERENCES NgheSi(ngheSiId) ON DELETE CASCADE;

-- Bảng BaiHat
ALTER TABLE BaiHat
ADD CONSTRAINT FK_BaiHat_TheLoai FOREIGN KEY (theLoaiId) 
REFERENCES TheLoai(theLoaiId) ON DELETE CASCADE;

ALTER TABLE BaiHat
ADD CONSTRAINT FK_BaiHat_NgheSi FOREIGN KEY (ngheSiId) 
REFERENCES NgheSi(ngheSiId) ON DELETE CASCADE;

-- Bảng ChiTietPlaylist
ALTER TABLE ChiTietPlaylist
ADD CONSTRAINT FK_ChiTietPlaylist_Playlist FOREIGN KEY (playlistId) 
REFERENCES Playlist(playlistId) ON DELETE CASCADE;

ALTER TABLE ChiTietPlaylist
ADD CONSTRAINT FK_ChiTietPlaylist_BaiHat FOREIGN KEY (baiHatId) 
REFERENCES BaiHat(baiHatId) ON DELETE CASCADE;

-- Bảng DanhGia
ALTER TABLE DanhGia
ADD CONSTRAINT FK_DanhGia_Users FOREIGN KEY (username) 
REFERENCES Users(username) ON DELETE CASCADE;

ALTER TABLE DanhGia
ADD CONSTRAINT FK_DanhGia_BaiHat FOREIGN KEY (baiHatId) 
REFERENCES BaiHat(baiHatId) ON DELETE CASCADE;

-- Bảng BinhLuan
ALTER TABLE BinhLuan
ADD CONSTRAINT FK_BinhLuan_Users FOREIGN KEY (username) 
REFERENCES Users(username) ON DELETE CASCADE;

ALTER TABLE BinhLuan
ADD CONSTRAINT FK_BinhLuan_BaiHat FOREIGN KEY (baiHatId) 
REFERENCES BaiHat(baiHatId) ON DELETE CASCADE;

-- Bảng LichSuNghe
ALTER TABLE LichSuNghe
ADD CONSTRAINT FK_LichSuNghe_Users FOREIGN KEY (username) 
REFERENCES Users(username) ON DELETE CASCADE;

ALTER TABLE LichSuNghe
ADD CONSTRAINT FK_LichSuNghe_BaiHat FOREIGN KEY (baiHatId) 
REFERENCES BaiHat(baiHatId) ON DELETE CASCADE;

select * from [dbo].[Album]
select * from [dbo].[BaiHat]
select * from [dbo].[NgheSi]
select * from [dbo].[TheLoai]

