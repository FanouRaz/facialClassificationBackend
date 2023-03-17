import joblib
from PIL import Image
import sys
import numpy as np

""" def hog_train (path_image):
    data=[]
    temp_img = imread(path_image)
    resize_temp_img = resize(temp_img,(64,64))
    fd,hod_image = hog(resize_temp_img,orientations=9,pixels_per_cell=(8,8),cells_per_block=(2,2),visualize=True)
    data.append(fd)
    return data
 """

path = sys.argv[1]
encoder = joblib.load("labelEncoder.joblib") 
model = joblib.load("model.joblib")
 
def preprocess(path_image):
    data=[]
    img = Image.open(path_image)
    data.append(img.getdata())
    return np.array(data)

d = preprocess(path)

print(encoder.inverse_transform(model.predict(d))[0])