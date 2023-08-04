FROM python:3.9
COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt
COPY . .
#ENV FLASK_APP=app.py
#EXPOSE 5000
#ENTRYPOINT ["python3"]
CMD ["python3", "app.py"]