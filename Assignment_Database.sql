USE [master]
GO
/****** Object:  Database [SE1611_PRJ_PROJECT]    Script Date: 3/25/2022 5:04:38 PM ******/
CREATE DATABASE [SE1611_PRJ_PROJECT]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SE1611_PRJ_PROJECT', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SE1611_PRJ_PROJECT.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SE1611_PRJ_PROJECT_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SE1611_PRJ_PROJECT_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SE1611_PRJ_PROJECT].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET ARITHABORT OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET  MULTI_USER 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET QUERY_STORE = OFF
GO
USE [SE1611_PRJ_PROJECT]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [nvarchar](150) NOT NULL,
	[password] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Account_Invoice]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account_Invoice](
	[username] [nvarchar](150) NOT NULL,
	[Invoice_ID] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_Account_Invoice] PRIMARY KEY CLUSTERED 
(
	[Invoice_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[AccountID] [int] NULL,
	[ProductID] [int] NULL,
	[Amount] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[cid] [int] NOT NULL,
	[cname] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[cid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Company]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Company](
	[CompanyID] [nvarchar](150) NOT NULL,
	[Company] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Company] PRIMARY KEY CLUSTERED 
(
	[CompanyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Company_Product]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Company_Product](
	[ProductID] [nvarchar](150) NOT NULL,
	[CompanyID] [nvarchar](150) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[gid] [int] NOT NULL,
	[gname] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[gid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupAccount]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupAccount](
	[username] [nvarchar](150) NOT NULL,
	[gid] [int] NOT NULL,
 CONSTRAINT [PK_GroupAccount] PRIMARY KEY CLUSTERED 
(
	[username] ASC,
	[gid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupPermission]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupPermission](
	[gid] [int] NOT NULL,
	[pid] [int] NOT NULL,
 CONSTRAINT [PK_GroupPermission] PRIMARY KEY CLUSTERED 
(
	[gid] ASC,
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[Invoice_ID] [nvarchar](150) NOT NULL,
	[CompanyID] [nvarchar](150) NOT NULL,
	[ProductID] [nvarchar](150) NOT NULL,
	[Amount] [int] NOT NULL,
	[Cost] [float] NOT NULL,
	[ImportDate] [date] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Permission]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Permission](
	[pid] [int] NOT NULL,
	[pname] [varchar](150) NOT NULL,
	[url] [varchar](1000) NOT NULL,
 CONSTRAINT [PK_Permission] PRIMARY KEY CLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [nvarchar](150) NOT NULL,
	[ProductName] [nvarchar](150) NOT NULL,
	[Stock] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Unit]    Script Date: 3/25/2022 5:04:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Unit](
	[UnitID] [nvarchar](150) NOT NULL,
	[UnitName] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_Method] PRIMARY KEY CLUSTERED 
(
	[UnitID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account_Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Account_Invoice_Account] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Account_Invoice] CHECK CONSTRAINT [FK_Account_Invoice_Account]
GO
ALTER TABLE [dbo].[Company_Product]  WITH CHECK ADD  CONSTRAINT [FK_Company_Product_Company] FOREIGN KEY([CompanyID])
REFERENCES [dbo].[Company] ([CompanyID])
GO
ALTER TABLE [dbo].[Company_Product] CHECK CONSTRAINT [FK_Company_Product_Company]
GO
ALTER TABLE [dbo].[Company_Product]  WITH CHECK ADD  CONSTRAINT [FK_Company_Product_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[Company_Product] CHECK CONSTRAINT [FK_Company_Product_Product]
GO
ALTER TABLE [dbo].[GroupAccount]  WITH CHECK ADD  CONSTRAINT [FK_GroupAccount_Account] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[GroupAccount] CHECK CONSTRAINT [FK_GroupAccount_Account]
GO
ALTER TABLE [dbo].[GroupAccount]  WITH CHECK ADD  CONSTRAINT [FK_GroupAccount_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([gid])
GO
ALTER TABLE [dbo].[GroupAccount] CHECK CONSTRAINT [FK_GroupAccount_Group]
GO
ALTER TABLE [dbo].[GroupPermission]  WITH CHECK ADD  CONSTRAINT [FK_GroupPermission_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([gid])
GO
ALTER TABLE [dbo].[GroupPermission] CHECK CONSTRAINT [FK_GroupPermission_Group]
GO
ALTER TABLE [dbo].[GroupPermission]  WITH CHECK ADD  CONSTRAINT [FK_GroupPermission_Permission] FOREIGN KEY([pid])
REFERENCES [dbo].[Permission] ([pid])
GO
ALTER TABLE [dbo].[GroupPermission] CHECK CONSTRAINT [FK_GroupPermission_Permission]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Company] FOREIGN KEY([CompanyID])
REFERENCES [dbo].[Company] ([CompanyID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Invoice_Company]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Account_Invoice] FOREIGN KEY([Invoice_ID])
REFERENCES [dbo].[Account_Invoice] ([Invoice_ID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Account_Invoice]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Product]
GO
USE [master]
GO
ALTER DATABASE [SE1611_PRJ_PROJECT] SET  READ_WRITE 
GO
